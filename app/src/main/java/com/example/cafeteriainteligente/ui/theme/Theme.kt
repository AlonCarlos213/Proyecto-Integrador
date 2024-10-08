package com.example.cafeteriainteligente.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Definición de Colores Personalizados
val Como500 = Color(0xFF427660)
val Como700 = Color(0xFF2B5143)
val Jade500 = Color(0xFF1ACD81)
val Jade600 = Color(0xFF0FA968)
val Aqua500 = Color(0xFF399390)
val Aqua600 = Color(0xFF2C7575)
val Vulcan500 = Color(0xFF646893)
val Vulcan600 = Color(0xFF4F517A)

// Definición del esquema de colores claro
private val LightColorScheme = lightColorScheme(
    primary = Jade500,
    onPrimary = Color.White,
    secondary = Como500,
    onSecondary = Color.White,
    tertiary = Aqua500,
    onTertiary = Color.White,
    background = Color(0xFFF7F7F7), // Fondo claro
    surface = Color.White,
    onBackground = Vulcan500,
    onSurface = Vulcan600
)

// Definición del esquema de colores oscuro
private val DarkColorScheme = darkColorScheme(
    primary = Jade600,
    onPrimary = Color.White,
    secondary = Como700,
    onSecondary = Color.White,
    tertiary = Aqua600,
    onTertiary = Color.White,
    background = Vulcan600, // Fondo oscuro
    surface = Vulcan500,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun CafeteriaInteligenteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

