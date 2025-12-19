package com.ars.paulet.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowCompat

/**
 * Tema Wabi-Sabi para Ars Paulet
 * Material 3 Expressive con filosofía japonesa
 */

// Esquema de colores claro (Vibrante)
private val WabiLightColorScheme = lightColorScheme(
    primary = WabiWood,
    onPrimary = WabiCream,
    primaryContainer = WabiSand,
    onPrimaryContainer = WabiBark,
    secondary = WabiMoss,
    onSecondary = WabiCream,
    secondaryContainer = WabiTea,
    onSecondaryContainer = WabiForest,
    tertiary = WabiIndigo,
    onTertiary = WabiCream,
    tertiaryContainer = WabiIndigoLight,
    onTertiaryContainer = WabiIndigoDark,
    error = WabiErrorLight,
    onError = WabiOnErrorLight,
    errorContainer = WabiErrorContainerLight,
    onErrorContainer = WabiOnErrorContainerLight,
    background = WabiBackgroundLight,
    onBackground = WabiOnBackgroundLight,
    surface = WabiSurfaceLight,
    onSurface = WabiOnSurfaceLight,
    surfaceVariant = WabiSurfaceVariantLight,
    onSurfaceVariant = WabiOnSurfaceVariantLight,
    outline = WabiStone,
    outlineVariant = WabiAsh
)

// Esquema de colores oscuro (Vibrante)
private val WabiDarkColorScheme = darkColorScheme(
    primary = WabiTerracotta,
    onPrimary = WabiBark,
    primaryContainer = WabiWoodDark,
    onPrimaryContainer = WabiCream,
    secondary = WabiBamboo,
    onSecondary = WabiForest,
    secondaryContainer = WabiMoss,
    onSecondaryContainer = WabiPaper,
    tertiary = WabiIndigoLight,
    onTertiary = WabiIndigoDark,
    tertiaryContainer = WabiIndigo,
    onTertiaryContainer = WabiPaper,
    error = WabiErrorDark,
    onError = WabiOnErrorDark,
    errorContainer = WabiErrorContainerDark,
    onErrorContainer = WabiOnErrorContainerDark,
    background = WabiBackgroundDark,
    onBackground = WabiOnBackgroundDark,
    surface = WabiSurfaceDark,
    onSurface = WabiOnSurfaceDark,
    surfaceVariant = WabiSurfaceVariantDark,
    onSurfaceVariant = WabiOnSurfaceVariantDark,
    outline = WabiSlate,
    outlineVariant = WabiCharcoal
)

@Composable
fun ArsPauletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> WabiDarkColorScheme
        else -> WabiLightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = WabiTypography,
        shapes = WabiShapes,
        content = content
    )
}

object WabiDimensions {
    val spacingXs = Dp(4f)
    val spacingSm = Dp(8f)
    val spacingMd = Dp(16f)
    val spacingLg = Dp(24f)
    val spacingXl = Dp(32f)
    val spacingXxl = Dp(48f)
    val elevationSm = Dp(2f)
    val elevationMd = Dp(4f)
    val elevationLg = Dp(8f)
    val iconSm = Dp(16f)
    val iconMd = Dp(24f)
    val iconLg = Dp(32f)
    val iconXl = Dp(48f)
    val buttonHeight = Dp(48f)
    val cardHeight = Dp(120f)
    val audioPlayerHeight = Dp(80f)
}
