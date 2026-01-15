package com.skash.galacticdirectory

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.skash.forge.navigation.NavigationDispatcher
import com.skash.forge.navigation.nav2.CollectNavigationEvents
import com.skash.forge.navigation.nav2.DefaultNavHost
import com.skash.galacticdirectory.designsystem.theme.AppTheme
import com.skash.galacticdirectory.di.appModule
import com.skash.galacticdirectory.navigation.Screen
import com.skash.galacticdirectory.navigation.TopLevelScreen
import com.skash.galacticdirectory.navigation.appGraph
import org.koin.compose.KoinMultiplatformApplication
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    KoinMultiplatformApplication(
        config = koinConfiguration {
            modules(appModule)
        }
    ) {

        val rootNavController = rememberNavController()
        val bottomNavController = rememberNavController()
        val navigationDispatcher = koinInject<NavigationDispatcher>()
        rootNavController.CollectNavigationEvents(navigationDispatcher)

        AppTheme {
            DefaultNavHost(
                navController = rootNavController,
                startDestination = TopLevelScreen.Graph
            ) {
                navigation<Screen.Graph>(
                    startDestination = Screen.Details(1)
                ) {
                    appGraph()
                }

                composable<TopLevelScreen.Graph> {
                    MainScreen(bottomNavController)
                }
            }
        }
    }
}