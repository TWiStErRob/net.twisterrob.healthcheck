plugins {
	id("org.jetbrains.kotlin.jvm") version "1.6.20"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.seleniumhq.selenium:selenium-java:4.1.3")
	implementation("io.github.bonigarcia:webdrivermanager:5.1.0")
	implementation("org.apache.logging.log4j:log4j-iostreams:2.17.2")
	implementation("org.assertj:assertj-core:3.22.0")

	val junitVersion = "5.8.2"
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
	testRuntimeOnly("org.junit.platform:junit-platform-console:1.8.2")
	testRuntimeOnly("org.slf4j:jul-to-slf4j:1.7.36")
	testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")
}

val javaVersion = JavaVersion.VERSION_1_8

plugins.withId("java") {
	val java = convention.getPluginByName<JavaPluginConvention>("java")
	java.sourceCompatibility = javaVersion
	java.targetCompatibility = javaVersion
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		verbose = true
		jvmTarget = javaVersion.toString()
		allWarningsAsErrors = true
	}
}

// Configure JUnit 5
tasks.withType<Test> {
	useJUnitPlatform {
	}
}

// Configure Console logging
tasks.withType<Test> {
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// Configure logging
tasks.withType<Test> {
	jvmArgs(
		"-Djava.util.logging.config.file=${rootProject.file("config/logging.properties")}"
	)
}
project.tasks {
	val sourceSets = project.the<JavaPluginConvention>().sourceSets
	val copyLoggingResources = register<Copy>("copyLoggingResources") {
		from(rootProject.file("config/log4j2.xml"))
		into(sourceSets["main"].resources.srcDirs.first())
	}
	"processResources" {
		dependsOn(copyLoggingResources)
	}
	val copyLoggingTestResources = register<Copy>("copyLoggingTestResources") {
		from(rootProject.file("config/log4j2.xml"))
		into(sourceSets["test"].resources.srcDirs.first())
	}
	"processTestResources"{
		dependsOn(copyLoggingTestResources)
	}
}

// Configure global test parameters.
tasks.withType<Test> {
	val propertyNamesToExposeToJUnitTests = listOf(
		"net.twisterrob.test.selenium.headless"
	)
	val properties = propertyNamesToExposeToJUnitTests.associateWith { project.findProperty(it) }
	properties.forEach { (name, value) -> inputs.property(name, value) }
	properties.forEach { (name, value) -> value?.let { jvmArgs("-D${name}=${value}") } }
}

tasks.register<Test>("smokeTest") {
	useJUnitPlatform {
		includeTags("smoke")
	}
}
