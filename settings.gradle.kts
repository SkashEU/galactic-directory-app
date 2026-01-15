import java.util.Properties




rootProject.name = "GalacticDirectory"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

val localProperties = Properties()
val localPropertiesFile = File(rootDir, "local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/SkashEU/forge")
            credentials {
                username = localProperties.getProperty("gpr.user")
                    ?: providers.gradleProperty("gpr.user").orNull

                password = localProperties.getProperty("gpr.key")
                    ?: providers.gradleProperty("gpr.key").orNull
            }
        }
    }
}

include(":composeApp")
include(":androidApp")

include(":domain")
include(":data")

include(":core:resource")
include(":core:designsystem")
include(":core:navigation")
include(":core:event")
include(":core:viewmodel")

include(":feature:detail:ui")
include(":feature:detail:presentation")
include(":feature:home:ui")
include(":feature:home:presentation")
