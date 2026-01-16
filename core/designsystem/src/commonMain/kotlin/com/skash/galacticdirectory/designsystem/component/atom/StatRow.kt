package com.skash.galacticdirectory.designsystem.component.atom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.resources.Res
import com.skash.galacticdirectory.resources.detail_stat_mass
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

data class StatItem(
    val icon: ImageVector,
    val label: StringResource,
    val value: String,
    val unit: StringResource? = null
)

@Composable
fun StatRow(item: StatItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.Medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(Spacing.Medium))

        Text(
            text = stringResource(item.label),
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) {
                    append(item.value)
                }
                item.unit?.let {
                    withStyle(SpanStyle(fontSize = 12.sp)) {
                        append(" $it")
                    }
                }
            }
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        StatRow(
            item = StatItem(
                icon = Icons.Default.Fingerprint,
                label = Res.string.detail_stat_mass,
                value = "Placeholder",
            )
        )
    }
}