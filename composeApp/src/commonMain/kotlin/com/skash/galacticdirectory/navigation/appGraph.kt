package com.skash.galacticdirectory.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.skash.forge.navigation.nav2.composableWithTransition
import com.skash.galacticdirectory.detail.ui.DetailScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


fun NavGraphBuilder.appGraph() {
    composableWithTransition<Screen.Details> {
        val parameter = it.toRoute<Screen.Details>()
        DetailScreen(viewModel = koinViewModel(parameters = { parametersOf(parameter.characterId) }))
    }
}