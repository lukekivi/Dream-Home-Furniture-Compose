package com.dreamhomefurniturecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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

val LocalDreamHomeColors = staticCompositionLocalOf {
    DreamHomeLightColors
}

@Composable
fun DreamHomeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DreamHomeLightColors
    } else {
        DreamHomeLightColors // todo make dark theme
    }

    val typography = AppTypography(
        screenHeader = TextStyle(
            color = colors.onHeaderBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        screenSubHeader1 = TextStyle(
            color = colors.onHeaderBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        screenSubHeader2 = TextStyle(
            color = colors.onHeaderBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        cardHeader = TextStyle(
            color = colors.cardContent,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        cardSubHeader1 = TextStyle(
            color = colors.cardContentSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        cardSubHeader2 = TextStyle(
            color = colors.cardContentSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        bodyHeader1 = TextStyle(
            color = colors.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        bodyHeader2 = TextStyle(
            color = colors.onBackground,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        body1 = TextStyle(
            color = colors.onBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        body2 = TextStyle(
            color = colors.onBackground,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Montserrat,
            textAlign = TextAlign.Left
        ),
        button = TextStyle(
            color = colors.buttonContent,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Left
        )
    )

    CompositionLocalProvider(
        LocalDreamHomeTypography provides typography,
        LocalDreamHomeColors provides colors
    ) {
        content()
    }
}

object DreamHomeTheme {
    val typography: AppTypography
        @Composable
        get() = LocalDreamHomeTypography.current
    val colors: AppColors
        @Composable
        get() = LocalDreamHomeColors.current
}