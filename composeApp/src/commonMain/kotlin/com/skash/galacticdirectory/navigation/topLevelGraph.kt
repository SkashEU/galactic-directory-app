package com.skash.galacticdirectory.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.topLevelGraph() {
    composable<TopLevelScreen.Home> {
        Text("Home")
    }

    composable<TopLevelScreen.Favorites> {
        Text("Favorites")
    }
}