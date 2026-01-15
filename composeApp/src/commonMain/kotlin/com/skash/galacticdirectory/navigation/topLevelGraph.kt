package com.skash.galacticdirectory.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skash.galacticdirectory.feature.home.ui.HomeScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.topLevelGraph() {
    composable<TopLevelScreen.Home> {
        HomeScreen(viewModel = koinViewModel())
    }

    composable<TopLevelScreen.Favorites> {
        Text("Favorites")
    }
}