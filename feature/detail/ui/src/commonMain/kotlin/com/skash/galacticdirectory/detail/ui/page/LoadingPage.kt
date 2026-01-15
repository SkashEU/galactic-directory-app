package com.skash.galacticdirectory.detail.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.template.Page

@Composable
internal fun LoadingPage() {
    Page(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        LoadingPage()
    }
}