package com.dreamhomefurniturecompose.ui.theme

import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val HalfBlack = Color(0x80000000)
val LightGray = Color(0xFFF5F3F3)
val Green = Color(0xFF30A743)
val DreamHomeBlue = Color(0xFF184C80)
val DreamHomeGray = Color(0xFF909090)

@Immutable
data class AppColors(
    val background: Color,
    val backgroundSecondary: Color,
    val onBackground: Color,
    val onBackgroundAccent: Color,
    val cardBackground: Color,
    val cardContent: Color,
    val cardContentSecondary: Color,
    val topAppBarBackground: Color,
    val topAppBarContent: Color,
    val headerBackground: Color,
    val onHeaderBackground: Color,
    val buttonBackground: Color,
    val buttonContent: Color
)

val DreamHomeLightColors = AppColors(
    background = White,
    backgroundSecondary = LightGray,
    onBackground = Black,
    onBackgroundAccent = HalfBlack,
    cardBackground = White,
    cardContent = DreamHomeBlue,
    cardContentSecondary = DreamHomeGray,
    topAppBarBackground = White,
    topAppBarContent = Black,
    headerBackground = DreamHomeBlue,
    onHeaderBackground = White,
    buttonBackground = DreamHomeBlue,
    buttonContent =  White
)