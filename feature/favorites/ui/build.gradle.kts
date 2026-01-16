plugins {
    alias(libs.plugins.galactic.ui.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.feature.favorites.ui"
    }

    sourceSets.commonMain.dependencies {
        implementation(projects.feature.favorites.presentation)
    }
}