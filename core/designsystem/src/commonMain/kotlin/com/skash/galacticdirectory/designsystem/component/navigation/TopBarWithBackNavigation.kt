package com.skash.galacticdirectory.designsystem.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackNavigation(onClickBack: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        TopBarWithBackNavigation { }
    }
}