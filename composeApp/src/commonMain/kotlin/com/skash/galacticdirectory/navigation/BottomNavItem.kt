package com.skash.galacticdirectory.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

internal data class BottomNavItem(
    val title: StringResource,
    val route: TopLevelScreen,
    val icon: ImageVector
)