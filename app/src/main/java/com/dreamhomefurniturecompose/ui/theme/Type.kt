package com.dreamhomefurniturecompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.dreamhomefurniturecompose.R
import javax.annotation.concurrent.Immutable

@Immutable
data class AppTypography(
    val screenHeader: TextStyle,
    val screenSubHeader1: TextStyle,
    val screenSubHeader2: TextStyle,
    val cardHeader: TextStyle,
    val cardSubHeader1: TextStyle,
    val cardSubHeader2: TextStyle,
    val bodyHeader1: TextStyle,
    val bodyHeader2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
)


val Montserrat = FontFamily(
    Font(resId = R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(resId = R.font.montserrat_black, weight = FontWeight.Black),
    Font(resId = R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold)
)