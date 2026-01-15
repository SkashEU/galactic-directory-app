package com.skash.galacticdirectory.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.skash.galacticdirectory.resources.Res
import com.skash.galacticdirectory.resources.RobotoMono_Bold
import com.skash.galacticdirectory.resources.RobotoMono_Regular
import com.skash.galacticdirectory.resources.RobotoMono_SemiBold
import com.skash.galacticdirectory.resources.michroma_regular
import org.jetbrains.compose.resources.Font

@Composable
fun appTypography(): Typography {
    val baseline = Typography()
    val bodyFontFamily = FontFamily(
        Font(Res.font.RobotoMono_Regular, weight = FontWeight.Normal),
        Font(Res.font.RobotoMono_Bold, weight = FontWeight.Bold),
        Font(Res.font.RobotoMono_SemiBold, weight = FontWeight.SemiBold)
    )

    val displayFontFamily = FontFamily(
        Font(Res.font.michroma_regular)
    )
    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
}

