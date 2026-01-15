package com.skash.galacticdirectory.designsystem.component.template

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.theme.Spacing

@Composable
private fun BasePage(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    clearFocusOnTouch: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (clearFocusOnTouch) {
                    Modifier.pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    }
                } else {
                    Modifier
                }
            )
            .then(modifier),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Composable
fun Page(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(Spacing.Medium),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    clearFocusOnTouch: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    BasePage(
        modifier = Modifier
            .padding(padding)
            .then(modifier),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        clearFocusOnTouch = clearFocusOnTouch,
        content = content
    )
}

@Composable
fun PageWithPaddingSlot(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(Spacing.Medium),
    clearFocusOnTouch: Boolean = true,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.(padding: PaddingValues) -> Unit,
) {
    BasePage(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        clearFocusOnTouch = clearFocusOnTouch
    ) {
        content(padding)
    }
}

@Preview
@Composable
private fun Preview() {
    ComponentPreviewTemplate {
        Page {
            Text(text = "Thats a page")

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {}) {
                Text("Some Action")
            }
        }
    }
}