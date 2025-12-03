package com.example.littlelemon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light theme colors
val LemonYellow = Color(0xFFFFE066)
val OliveGreen = Color(0xFF6A994E)
val DarkGray = Color(0xFF333333)

// Define a proper light color scheme
private val LightColors = lightColorScheme(
    primary = LemonYellow,
    secondary = OliveGreen,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray
)

@Composable
fun LittleLemonTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,

        content = content
    )
}
