package com.example.atonce_admin.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    background = LightBackground,
    surface = LightBackground,
    onPrimary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    background = DarkBackground,
    surface = DarkBackground,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)


@Composable
fun AtOnceAdminTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
