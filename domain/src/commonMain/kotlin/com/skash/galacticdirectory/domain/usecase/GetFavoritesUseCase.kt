package com.skash.galacticdirectory.domain.usecase

import com.skash.forge.usecase.FlowUseCase
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val characterRepository: CharacterRepository
): FlowUseCase<Unit, List<CharacterWithDetails>>() {

    override fun execute(params: Unit): Flow<List<CharacterWithDetails>> {
        return characterRepository.observeFavorites()
    }
}