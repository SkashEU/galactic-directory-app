import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class UIFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPluginsByName("galactic-compose-library")

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain {
                    dependencies {
                        listOf(
                            ":core:designsystem",
                            ":domain",
                            ":core:resource",
                            ":core:navigation",
                            ":core:viewmodel"
                        ).forEach { module ->
                            implementation(project(module))
                        }
                    }
                }
            }
        }
    }
}