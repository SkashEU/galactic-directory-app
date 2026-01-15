package com.skash.galacticdirectory.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.theme.Spacing

@Composable
fun DetailCard(
    icon: ImageVector,
    text: String
) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.Medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(Modifier.width(Spacing.Large))
            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        DetailCard(
            icon = Icons.Default.Fingerprint,
            text = "Human"
        )
    }
}