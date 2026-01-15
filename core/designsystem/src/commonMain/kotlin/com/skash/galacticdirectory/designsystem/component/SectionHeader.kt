package com.skash.galacticdirectory.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.theme.Spacing

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = Spacing.Small)
    )
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        SectionHeader(
            text = "Species"
        )
    }
}