package com.example.rickandmorty.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LessBlack,
    onPrimary = RickColor,
    background = Color.Black,
    onBackground = Color.White,
    primaryVariant = blackTransparent

)

private val LightColorPalette = lightColors(
    primary = RickColor,
    onPrimary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    primaryVariant = whiteTransparent

)

@Composable
fun RickAndMortyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}