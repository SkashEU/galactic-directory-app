plugins {
    alias(libs.plugins.galactic.kmp.library)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.viewmodel"
    }

    sourceSets.commonMain.dependencies {
        implementation(project.dependencies.platform(libs.koin.bom))
        implementation(libs.koin.compose)
        api(libs.forge.viewmodel)
        api(projects.core.event)
    }
}