package com.skash.galacticdirectory.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.feature.home.presentation.HomeState
import com.skash.galacticdirectory.feature.home.presentation.HomeViewModel
import com.skash.galacticdirectory.feature.home.ui.page.HomePage
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val state by viewModel.collectStateFlow().collectAsStateWithLifecycle()

    HomeScreenImpl(
        state = state,
        pagingData = viewModel.characterPagingData,
        executeIntent = viewModel::executeIntent
    )

}

@Composable
private fun HomeScreenImpl(
    state: HomeState,
    pagingData: Flow<PagingData<Character>>,
    executeIntent: (HomeState.Intent) -> Unit
) {
    HomePage(
        query = state.query,
        pagingData = pagingData,
        onQueryChange = { executeIntent(HomeState.Intent.SetQuery(it)) }
    )
}