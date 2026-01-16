package com.skash.galacticdirectory.feature.favorites.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skash.galacticdirectory.feature.favorites.presentation.FavoritesState
import com.skash.galacticdirectory.feature.favorites.presentation.FavoritesViewModel
import com.skash.galacticdirectory.feature.favorites.ui.page.EmptyFavoritesPage
import com.skash.galacticdirectory.feature.favorites.ui.page.FavoritesPage

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel
) {

    val state by viewModel.collectStateFlow().collectAsStateWithLifecycle()

    HomeScreenImpl(
        state = state,
        executeIntent = viewModel::executeIntent
    )

}

@Composable
private fun HomeScreenImpl(
    state: FavoritesState,
    executeIntent: (FavoritesState.Intent) -> Unit
) {

    when (state.favorites.isEmpty()) {
        true -> EmptyFavoritesPage()
        false -> FavoritesPage(
            favorites = state.favorites,
            onCharacterClick = { executeIntent(FavoritesState.Intent.NavigateToDetails(it)) },
            onToggleFavorite = {executeIntent(FavoritesState.Intent.ToggleIsFavorite(it))}
        )
    }
}