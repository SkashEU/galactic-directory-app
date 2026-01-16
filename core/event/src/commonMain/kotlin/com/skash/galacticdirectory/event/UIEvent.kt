package com.skash.galacticdirectory.event

sealed interface UIEvent {
    data class SnackBar(val message: String) : UIEvent
}
