package com.skash.galacticdirectory.detail.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.template.Page
import com.skash.galacticdirectory.resources.Res
import com.skash.galacticdirectory.resources.detail_error_archives_incomplete
import com.skash.galacticdirectory.resources.detail_error_lost_page
import com.skash.galacticdirectory.resources.detail_error_return_to_base
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EntityNotAvailablePage(
    onClickBack: () -> Unit
) {
    Page(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Outlined.SearchOff,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(Res.string.detail_error_archives_incomplete),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.detail_error_lost_page),
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onClickBack,
        ) {
            Text(text = stringResource(Res.string.detail_error_return_to_base))
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        EntityNotAvailablePage(onClickBack = {})
    }
}
