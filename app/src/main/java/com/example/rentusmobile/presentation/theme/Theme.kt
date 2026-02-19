package com.example.rentusmobile.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    background = Background,
    surface = Surface,
    onPrimary = White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = Error
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    background = Background,
    surface = Surface,
    onPrimary = White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = Error
)

@Composable
fun RentusMobileTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
