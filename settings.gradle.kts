rootProject.name = "net-twisterrob-healthcheck"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version ("0.6.0")
}
