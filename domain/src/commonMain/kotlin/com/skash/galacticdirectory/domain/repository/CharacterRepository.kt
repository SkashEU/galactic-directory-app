package com.skash.galacticdirectory.domain.repository

import androidx.paging.PagingData
import com.skash.galacticdirectory.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getPaginatedCharacter(query: String): Flow<PagingData<Character>>
}