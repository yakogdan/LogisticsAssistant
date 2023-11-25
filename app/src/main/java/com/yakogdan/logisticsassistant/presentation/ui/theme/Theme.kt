package com.yakogdan.logisticsassistant.presentation.ui.theme

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

private val darkColorScheme = darkColorScheme(
    primary = Color(0xFF000000),
    secondary = DarkGray,
    tertiary = Pink80,
    background = Color(0xFF000000),
    surface = Color(0xFF000000),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color.White,
    onSurface = Color(0xFFE9E4F5),
    outline = DarkGray,
    outlineVariant = Gray,
    error = RedError
)

private val lightColorScheme = lightColorScheme(
    primary = Dark,
    secondary = DarkGray,
    tertiary = Gray,
    primaryContainer = Dark,
    onPrimaryContainer = Color.White,
    secondaryContainer = Gray,
    onSecondaryContainer = MiddleGrayBlue,
    background = Color.White,
    surface = Color(0xFFCECECE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = MiddleGrayBlue,
    onBackground = Dark,
    onSurface = Color(0xFF1C1B1F),
    outline = DarkGray,
    outlineVariant = Gray,
    error = RedError
)

@Composable
fun LogisticsAssistantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}