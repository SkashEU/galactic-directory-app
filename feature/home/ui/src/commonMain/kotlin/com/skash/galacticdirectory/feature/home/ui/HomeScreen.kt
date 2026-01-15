package com.skash.galacticdirectory.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skash.galacticdirectory.feature.home.presentation.HomeState
import com.skash.galacticdirectory.feature.home.presentation.HomeViewModel
import com.skash.galacticdirectory.feature.home.ui.page.HomePage

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val state by viewModel.collectStateFlow().collectAsStateWithLifecycle()

    HomeScreenImpl(
        state = state,
        executeIntent = viewModel::executeIntent
    )

}

@Composable
private fun HomeScreenImpl(
    state: HomeState,
    executeIntent: (HomeState.Intent) -> Unit
) {
    HomePage(
        query = state.query,
        onQueryChange = { executeIntent(HomeState.Intent.SetQuery(it)) }
    )
}