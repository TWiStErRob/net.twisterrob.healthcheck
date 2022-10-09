plugins {
	@Suppress("DSL_SCOPE_VIOLATION")
	alias(libs.plugins.kotlin)
}

dependencies {
	implementation(libs.kotlin.stdlib8)
	implementation(libs.kotlin.reflect)

	implementation(libs.selenium)
	implementation(libs.webdrivermanager)
	// TODEL when upgrading webdrivermanager Listed because they have vulnerabilities.
	implementation(libs.jackson.databind)
	implementation(libs.commons.io)
	implementation(libs.bouncycastle.jdk15)

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
	sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
	targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	kotlinOptions {
		verbose = true
		jvmTarget = libs.versions.java.get()
		allWarningsAsErrors = true
	}
}

// Configure JUnit 5
tasks.withType<Test>().configureEach {
	useJUnitPlatform {
	}
}

// Configure Console logging
tasks.withType<Test>().configureEach {
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// Configure logging
tasks.withType<Test>().configureEach {
	jvmArgs(
		"-Djava.util.logging.config.file=${rootProject.file("config/logging.properties")}"
	)
}
project.tasks {
	val copyLoggingResources = register<Copy>("copyLoggingResources") {
		from(rootProject.file("config/log4j2.xml"))
		into(java.sourceSets["main"].resources.srcDirs.first())
	}
	"processResources" {
		dependsOn(copyLoggingResources)
	}
	val copyLoggingTestResources = register<Copy>("copyLoggingTestResources") {
		from(rootProject.file("config/log4j2.xml"))
		into(java.sourceSets["test"].resources.srcDirs.first())
	}
	"processTestResources" {
		dependsOn(copyLoggingTestResources)
	}
}

// Configure global test parameters.
tasks.withType<Test>().configureEach {
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
