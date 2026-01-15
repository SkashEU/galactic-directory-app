package com.skash.galacticdirectory.domain.usecase

import com.skash.forge.outcome.Outcome
import com.skash.forge.usecase.FlowOutcomeUseCase
import com.skash.galacticdirectory.domain.error.ErrorType
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch

class GetCharacterWithDetailsUseCase(
    private val characterRepository: CharacterRepository,
) : FlowOutcomeUseCase<Int, CharacterWithDetails, ErrorType>() {
    override suspend fun FlowCollector<Outcome<CharacterWithDetails, ErrorType>>.execute(
        params: Int
    ) {
        characterRepository.getCharacterDetailsAsFlow(params)
            .catch { e ->
                emit(Outcome.Failure(ErrorType.DetailsFetchFailed))
            }
            .collect { data ->
                emit(Outcome.Success(data))
            }
    }
}