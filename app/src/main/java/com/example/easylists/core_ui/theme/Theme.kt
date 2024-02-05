package com.example.easylists.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BlueSea,
    primaryVariant = BlueSeaVariant,
    secondary = GreenGrass,

    background = Color.Black,
    surface = Color.DarkGray,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = BlueSea,
    onSurface = BlueSea
)

private val LightColorPalette = lightColors(
    primary = DarkBlueSea,
    primaryVariant = DarkBlueSeaVariant,
    secondary = DarkGreenGrass,

    background = Color.White,
    surface = Color.LightGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = DarkBlueSea,
    onSurface = DarkBlueSea
)

@Composable
fun EasyListsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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