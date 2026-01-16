plugins {
    alias(libs.plugins.galactic.compose.library)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.resource"
        androidResources { enable = true }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.skash.galacticdirectory.resources"
}