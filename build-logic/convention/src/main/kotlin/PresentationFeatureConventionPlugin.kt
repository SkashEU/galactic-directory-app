import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class PresentationFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPluginsByName("galactic-kmp-library")

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain {
                    dependencies {
                        listOf(
                            ":core:resource",
                            ":domain",
                            ":core:event",
                            ":core:viewmodel"
                        ).forEach { module ->
                            api(project(module))
                        }
                    }
                }
            }
        }
    }
}