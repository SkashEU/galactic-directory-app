package com.skash.galacticdirectory.feature.home.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skash.galacticdirectory.domain.usecase.GetCharactersUseCase
import com.skash.galacticdirectory.viewmodel.BaseViewModel

class HomeViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
): BaseViewModel<HomeState, HomeState.Intent>(initialState = HomeState()) {

    val characterPagingData = getCharactersUseCase("")
        .cachedIn(viewModelScope)

    override fun executeIntent(intent: HomeState.Intent) {
        TODO("Not yet implemented")
    }
}