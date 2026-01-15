import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            applyPluginsByName(
                "kotlinMultiplatform",
                "androidKotlinMultiplatformLibrary"
            )

            extensions.configure<KotlinMultiplatformExtension> {
                jvmToolchain(JVM_TOOLCHAIN_VERSION)

                targets.withType<KotlinMultiplatformAndroidLibraryTarget> {
                    compileSdk = COMPILE_SDK
                    minSdk = MIN_SDK
                }

                iosX64 {
                    binaries.framework {
                        baseName = xcFrameworkName
                    }
                }
                iosArm64 {
                    binaries.framework {
                        baseName = xcFrameworkName
                    }
                }
                iosSimulatorArm64 {
                    binaries.framework {
                        baseName = xcFrameworkName
                    }
                }

                sourceSets.androidMain {
                    dependencies {
                        implementation(findLibraryByName("kotlinx-coroutines-android"))
                    }
                }

                sourceSets.commonMain {
                    dependencies {
                        implementation(findLibraryByName("kotlin-stdlib"))
                        implementation(findLibraryByName("kotlinx-coroutines-core"))
                    }
                }

                compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}