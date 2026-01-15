package com.skash.galacticdirectory.domain.usecase

import androidx.paging.PagingData
import com.skash.forge.usecase.FlowUseCase
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
): FlowUseCase<String, PagingData<Character>>() {

    override fun execute(params: String): Flow<PagingData<Character>> {
        return characterRepository.getPaginatedCharacter(params)
    }
}