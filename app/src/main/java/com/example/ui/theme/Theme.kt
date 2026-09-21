package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LuxuryDarkColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = ObsidianBlack,
    primaryContainer = GoldDark,
    onPrimaryContainer = GoldLight,
    secondary = GoldBright,
    onSecondary = ObsidianBlack,
    secondaryContainer = CardCharcoal,
    onSecondaryContainer = GoldLight,
    tertiary = GoldMuted,
    onTertiary = ObsidianBlack,
    background = ObsidianBlack,
    onBackground = TextWhite,
    surface = DeepCharcoal,
    onSurface = TextWhite,
    surfaceVariant = SurfaceCharcoal,
    onSurfaceVariant = TextSilver,
    outline = BorderCharcoal,
    outlineVariant = GoldBorder
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LuxuryDarkColorScheme,
        typography = Typography,
        content = content
    )
}

