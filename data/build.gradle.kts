plugins {
    alias(libs.plugins.galactic.kmp.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.room)
}

kotlin {
    android {
        namespace = "com.skash.galacticdirectory.data"
    }

    sourceSets.commonMain.dependencies {
        implementation(libs.kotlinx.serialization.json)
        implementation(libs.forge.network.client)
        api(libs.androidx.paging.common)
        api(projects.domain)

        api(libs.androidx.room.runtime)
        implementation(libs.sqlite.bundled)
        implementation(libs.androidx.startup.runtime)
        implementation(libs.androidx.room.paging)

    }
}

room {
    schemaDirectory("$projectDir/schemas")
}


dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}