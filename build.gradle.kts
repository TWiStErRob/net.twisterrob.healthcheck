plugins {
	id("org.jetbrains.kotlin.jvm") version "1.5.31"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.seleniumhq.selenium:selenium-java:3.14.0")
	implementation("org.assertj:assertj-core:3.11.1")

	val junitVersion = "5.3.0"
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
	testRuntimeOnly("org.junit.platform:junit-platform-console:1.3.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		verbose = true
		allWarningsAsErrors = true
	}
}

tasks.withType<Test> {
	useJUnitPlatform {
	}
	testLogging {
		events("passed", "skipped", "failed")
	}
}

tasks.register<Test>("smokeTest") {
	useJUnitPlatform {
		includeTags("smoke")
	}
}
