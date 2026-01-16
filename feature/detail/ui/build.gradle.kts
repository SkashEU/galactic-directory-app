plugins {
    alias(libs.plugins.galactic.ui.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.feature.detail.ui"
    }

    sourceSets.commonMain.dependencies {
        implementation(projects.feature.detail.presentation)
    }
}