package com.skash.galacticdirectory.designsystem.component.atom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.theme.Spacing

@Composable
fun MetaTag(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(Spacing.Medium),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 80.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ComponentPreviewTemplate {
        MetaTag(
            icon = Icons.Default.Fingerprint,
            text = "Human"
        )
    }
}