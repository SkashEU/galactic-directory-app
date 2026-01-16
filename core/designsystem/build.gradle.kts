plugins {
    alias(libs.plugins.galactic.compose.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.designsystem"
    }

    sourceSets.commonMain.dependencies {
        api(projects.core.resource)
    }
}