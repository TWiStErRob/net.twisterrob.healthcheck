plugins {
	alias(libs.plugins.kotlin)
}

dependencies {
	implementation(libs.kotlin.stdlib8)
	implementation(libs.kotlin.reflect)

	implementation(libs.selenium)
	implementation(libs.selenium.jdkHttp)
	implementation(libs.webdrivermanager)

	implementation(libs.log4j.iostreams)
	implementation(libs.assertj)

	testImplementation(libs.junit.api)
	testImplementation(libs.junit.params)
	testRuntimeOnly(libs.junit.engine)

	testRuntimeOnly(libs.log4j)
	testRuntimeOnly(libs.log4j.slf4j)
	testRuntimeOnly(libs.slf4j.jul)
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
	}
}

kotlin {
	jvmToolchain {
		languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
	}
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	compilerOptions {
		verbose = true
		allWarningsAsErrors = true
	}
}

registerCopyLoggingFor(java.sourceSets["main"])

@Suppress("UnstableApiUsage")
testing.suites {
	withType<JvmTestSuite>().configureEach {
		registerCopyLoggingFor(sources)
		useJUnitJupiter(libs.versions.junit)
		targets.configureEach {
			testTask.configure {
				// Enable console logging.
				testLogging { events("passed", "skipped", "failed") }
				// Logging configuration.
				jvmArgs("-Djava.util.logging.config.file=${rootProject.file("config/jul.properties")}")
				// Share CI parameters with test.
				exposePropertiesToTest(
					"net.twisterrob.test.selenium.headless",
					"wdm.chromeDriverVersion",
				)
				// Relocate WebDriverManager cache from ~/.cache/selenium to build directory.
				val wdmCache = rootProject.layout.buildDirectory.dir("webdrivermanager-cache")
				outputs.dir(wdmCache).withPropertyName("wdm.cachePath")
				systemProperty("wdm.cachePath", wdmCache.get().asFile)
			}
		}
	}
	val test by existing(JvmTestSuite::class) {
		testType.set(TestSuiteType.INTEGRATION_TEST)
	}
	register<JvmTestSuite>("smokeTest") {
		testType.set("smoke-test")
		targets.configureEach {
			testTask.configure {
				options {
					this as JUnitPlatformOptions
					includeTags("smoke")
				}
				// Share classpath with test, don't use own.
				testClassesDirs = files(test.map { it.sources.output.classesDirs })
				classpath = files(test.map { it.sources.runtimeClasspath })
			}
		}
	}
}

fun Test.exposePropertiesToTest(vararg propertyNames: String) {
	val properties = propertyNames.associateWith { project.findProperty(it) }
	properties.forEach { (name, value) -> inputs.property(name, value) }
	properties.forEach { (name, value) -> value?.let { systemProperty(name, value) } }
}

fun Project.registerCopyLoggingFor(sourceSet: SourceSet) {
	val copy = tasks.register<Copy>(sourceSet.getTaskName("copyLogging", "resources")) {
		from(project.rootProject.file("config/log4j2.xml"))
		into(sourceSet.resources.srcDirs.first())
	}

	tasks.named(sourceSet.processResourcesTaskName) {
		dependsOn(copy)
	}
}
