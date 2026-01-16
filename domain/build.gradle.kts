plugins {
    alias(libs.plugins.galactic.kmp.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.domain"
    }

    sourceSets.commonMain.dependencies {
        api(libs.androidx.paging.common)
        api(libs.forge.usecase)
    }
}