package com.skash.galacticdirectory.domain.error

sealed class ErrorType(val message: String) {
    data object DetailsFetchFailed: ErrorType("Failed to fetch character details")
    data object FavoriteToggleFailed: ErrorType("Failed to toggle favorite state")
}