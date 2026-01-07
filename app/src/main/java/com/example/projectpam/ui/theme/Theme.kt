package com.example.projectpam.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = White,
    secondary = DarkSecondary,
    onSecondary = NeutralGray90,
    tertiary = Teal40,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = White,
    surfaceVariant = DarkSurface, // Use DarkSurface for variants
    onSurfaceVariant = NeutralGray10 // Light gray for text on variants
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,
    secondary = AccentOrange,
    onSecondary = NeutralGray90,
    tertiary = Teal40,
    background = NeutralGray10,
    surface = White,
    onSurface = NeutralGray90,
    surfaceVariant = PrimaryBlueLight, // A light blue for card backgrounds
    onSurfaceVariant = PrimaryBlueDark // A dark blue for text on cards
)

@Composable
fun ProjectpamTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
