package com.example.pelatihankode.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = LavenderPrimary,
    onPrimary = LavenderText,
    primaryContainer = LavenderSecondary,
    onPrimaryContainer = LavenderText,
    secondary = LavenderSecondary,
    onSecondary = LavenderText,
    secondaryContainer = LavenderPrimary,
    onSecondaryContainer = LavenderText,
    tertiary = LavenderPrimary,
    onTertiary = LavenderText,
    background = LavenderSurface,
    onBackground = LavenderText,
    surface = LavenderSurface,
    onSurface = LavenderText,
    surfaceVariant = LavenderSurfaceVariant,
    onSurfaceVariant = LavenderMutedText,
    outline = LavenderOutline
)

@Composable
fun PelatihanKODETheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
