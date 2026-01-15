plugins {
    alias(libs.plugins.galactic.ui.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.feature.home.ui"
    }

    sourceSets.commonMain.dependencies {
        implementation(projects.feature.home.presentation)
    }
}