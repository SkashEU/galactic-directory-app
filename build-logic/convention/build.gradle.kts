plugins {
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id =
                libs.plugins.galactic.kmp.library
                    .get()
                    .pluginId
            implementationClass = "KMPLibraryConventionPlugin"
        }
        register("composeLibrary") {
            id =
                libs.plugins.galactic.compose.library
                    .get()
                    .pluginId
            implementationClass = "ComposeLibraryConventionPlugin"
        }
    }
}
