package com.dreamhomefurniturecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

private val DarkColorPalette = darkColors(
    primary = White,
    primaryVariant = White,
    secondary = White
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = White,
    secondary = White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val LocalDreamHomeTypography = staticCompositionLocalOf {
    AppTypography(
        screenHeader = TextStyle.Default,
        screenSubHeader1 = TextStyle.Default,
        screenSubHeader2 = TextStyle.Default,
        cardHeader = TextStyle.Default,
        cardSubHeader1 = TextStyle.Default,
        cardSubHeader2 = TextStyle.Default,
        bodyHeader1 = TextStyle.Default,
        bodyHeader2 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        button = TextStyle.Default
    )
}

@Composable
fun DreamHomeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = DreamHomeTypography
    
    CompositionLocalProvider(
        LocalDreamHomeTypography provides DreamHomeTypography
    ) {
        content()
    }
}

object DreamHomeTheme {
    val typography: AppTypography
        @Composable
        get() = LocalDreamHomeTypography.current
}