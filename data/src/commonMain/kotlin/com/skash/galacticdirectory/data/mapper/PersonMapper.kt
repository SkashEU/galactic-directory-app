package com.skash.galacticdirectory.data.mapper

import com.skash.galacticdirectory.data.database.entity.PersonEntity
import com.skash.galacticdirectory.domain.model.Character

fun PersonEntity.toCharacter(): Character = Character(
    id = id,
    name = name,
    birthYear = "abc"
)