package com.skash.galacticdirectory.feature.home.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skash.forge.navigation.NavigationEvent
import com.skash.galacticdirectory.domain.usecase.GetCharactersUseCase
import com.skash.galacticdirectory.navigation.Screen
import com.skash.galacticdirectory.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel<HomeState, HomeState.Intent>(initialState = HomeState()) {

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val characterPagingData = collectStateFlow()
        .map { it.query }
        .distinctUntilChanged()
        .debounce(300)
        .flatMapLatest { getCharactersUseCase(it) }
        .cachedIn(viewModelScope)

    override fun executeIntent(intent: HomeState.Intent) {
        when (intent) {
            is HomeState.Intent.SetQuery -> reduceState<HomeState> { copy(query = intent.query) }
            is HomeState.Intent.NavigateToDetails -> handleIntent<_, _>(
                intent = intent,
                handler = ::handleNavigateToDetails
            )
        }
    }

    private fun handleNavigateToDetails(
        state: HomeState,
        intent: HomeState.Intent.NavigateToDetails
    ) {
        val navigationEvent = NavigationEvent.NavigateTo(Screen.Details(intent.id))
        dispatchNavigationEvent(navigationEvent)
    }
}