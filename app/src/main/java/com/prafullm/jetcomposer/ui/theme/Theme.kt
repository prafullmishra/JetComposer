package com.prafullm.jetcomposer.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = darkColors(
    primary = White,
    primaryVariant = Black50,
    secondary = Black300,
    background = Black50,
    surface = White,
    onSurface = Black,
    onBackground = Black
)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = lightColors(
    primary = Black700,
    primaryVariant = Black500,
    secondary = Black100,
    background = Black,
    surface = Black900,
    onSurface = Black50,
    onBackground = Black70
)

@Composable
fun JetComposerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}