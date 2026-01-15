package com.skash.galacticdirectory.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.theme.AppTheme

@Composable
fun ComponentPreviewTemplate(
    content: @Composable ColumnScope.() -> Unit
) {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize()) { content() }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        Text(text = "Composable to wrap component in theme. Use this in all previews")
    }
}