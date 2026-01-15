import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            applyPluginsByName(
                "galactic-kmp-library",
                "composeMultiplatform",
                "composeCompiler"
            )

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain {
                    dependencies {
                        implementation(findLibraryByName("compose-runtime"))
                        implementation(findLibraryByName("compose-foundation"))
                        implementation(findLibraryByName("compose-ui"))
                        api(findLibraryByName("compose-material3"))
                        implementation(findLibraryByName("compose-material-icons"))
                        implementation(findLibraryByName("compose-animation"))
                        implementation(findLibraryByName("compose-animation-graphics"))
                        implementation(findLibraryByName("androidx-lifecycle-runtimeCompose"))

                        implementation(findLibraryByName("compose-components-resources"))
                        implementation(findLibraryByName("compose-ui-tooling-preview"))
                    }
                }

                sourceSets.named("androidMain") {
                    dependencies {
                        implementation(findLibraryByName("compose-ui-tooling"))
                    }
                }
            }
        }
    }
}