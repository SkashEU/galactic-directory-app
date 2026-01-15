plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {

    android {
        namespace = "com.skash.galaticdirectory.app"
        compileSdk { version = release(libs.versions.android.targetSdk.get().toInt()) }
        minSdk { version = release(libs.versions.android.minSdk.get().toInt()) }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {

            implementation(projects.feature.home.presentation)
            implementation(projects.feature.home.ui)

            implementation(projects.feature.detail.presentation)
            implementation(projects.feature.detail.ui)

            implementation(projects.core.event)
            implementation(libs.forge.event)
            implementation(libs.forge.navigation.nav2)
            implementation(libs.navigation.compose)
            implementation(projects.core.navigation)
            implementation(projects.core.designsystem)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(projects.data)
            implementation(projects.domain)

            implementation(libs.forge.network.ktor)


            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

