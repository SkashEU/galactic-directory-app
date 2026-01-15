package com.skash.galacticdirectory.data.util

internal fun String.extractId(): Int = this.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0