plugins {
    alias(libs.plugins.galactic.kmp.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.navigation"
    }

    sourceSets.commonMain.dependencies {
        implementation(libs.kotlinx.serialization.json)
    }
}