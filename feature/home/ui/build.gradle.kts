plugins {
    alias(libs.plugins.galactic.ui.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.feature.home.ui"
    }

    sourceSets.commonMain.dependencies {
        implementation(projects.feature.home.presentation)
        implementation("androidx.paging:paging-compose:3.4.0-rc01")
    }
}