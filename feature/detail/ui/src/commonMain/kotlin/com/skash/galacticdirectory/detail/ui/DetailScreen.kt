package com.skash.galacticdirectory.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skash.galacticdirectory.designsystem.component.navigation.TopBarWithBackNavigation
import com.skash.galacticdirectory.designsystem.component.template.Screen
import com.skash.galacticdirectory.detail.presentation.DetailState
import com.skash.galacticdirectory.detail.presentation.DetailViewModel
import com.skash.galacticdirectory.detail.ui.page.EntityNotAvailablePage
import com.skash.galacticdirectory.detail.ui.page.LoadedPage
import com.skash.galacticdirectory.detail.ui.page.LoadingPage

@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {

    val state by viewModel.collectStateFlow().collectAsStateWithLifecycle()

    DetailScreenImpl(
        state = state,
        executeIntent = viewModel::executeIntent
    )
}

@Composable
private fun DetailScreenImpl(
    state: DetailState,
    executeIntent: (DetailState.Intent) -> Unit
) {
    Screen(
        topBar = {
            TopBarWithBackNavigation(onClickBack = { executeIntent(DetailState.Intent.NavigateBack) })
        }
    ) {
        when (state) {
            DetailState.EntityNotAvailable -> EntityNotAvailablePage(onClickBack = {
                executeIntent(
                    DetailState.Intent.NavigateBack
                )
            })

            DetailState.Loading -> LoadingPage()
            is DetailState.Loaded -> LoadedPage(
                characterWithDetails = state.details,
                onToggleFavorite = { executeIntent(DetailState.Loaded.Intent.ToggleIsFavorite) }
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {

}