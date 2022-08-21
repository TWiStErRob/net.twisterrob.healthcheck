plugins {
	id("org.jetbrains.kotlin.jvm") version "1.7.10"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.seleniumhq.selenium:selenium-java:4.4.0")
	implementation("io.github.bonigarcia:webdrivermanager:5.3.0")
	// TODEL when upgrading webdrivermanager Listed because they have vulnerabilities.
	implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
	implementation("commons-io:commons-io:2.11.0")
	implementation("org.bouncycastle:bcprov-jdk15on:1.70")

	implementation("org.apache.logging.log4j:log4j-iostreams:2.18.0")
	implementation("org.assertj:assertj-core:3.23.1")

	val junitVersion = "5.9.0"
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
	testRuntimeOnly("org.junit.platform:junit-platform-console:1.9.0")
	testRuntimeOnly("org.slf4j:jul-to-slf4j:2.0.0")
	testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.18.0")
}

val javaVersion = JavaVersion.VERSION_1_8

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
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
