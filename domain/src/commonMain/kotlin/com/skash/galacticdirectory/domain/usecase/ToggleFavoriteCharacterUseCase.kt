package com.skash.galacticdirectory.domain.usecase

import com.skash.forge.usecase.UseCase
import com.skash.galacticdirectory.domain.error.ErrorType
import com.skash.galacticdirectory.domain.repository.CharacterRepository

class ToggleFavoriteCharacterUseCase(
    private val characterRepository: CharacterRepository
) : UseCase<ToggleFavoriteCharacterUseCase.Input, Unit, ErrorType>() {
    override suspend fun UseCaseScope<ErrorType>.execute(
        params: Input
    ) {
        characterRepository.setFavorite(params.characterId, params.isFavorite)
    }

    override fun mapError(t: Throwable): ErrorType {
        return ErrorType.FavoriteToggleFailed
    }

    data class Input(
        val characterId: Int,
        val isFavorite: Boolean
    )
}