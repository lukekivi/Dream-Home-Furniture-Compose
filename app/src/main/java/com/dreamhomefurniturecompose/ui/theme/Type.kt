package com.dreamhomefurniturecompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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

// Set of Material typography styles to start with
val DreamHomeTypography = AppTypography(
    screenHeader = TextStyle(
        color = White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    screenSubHeader1 = TextStyle(
        color = White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    screenSubHeader2 = TextStyle(
        color = White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    cardHeader = TextStyle(
        color = DreamHomeBlue,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    cardSubHeader1 = TextStyle(
        color = DreamHomeGray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    cardSubHeader2 = TextStyle(
        color = DreamHomeGray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    bodyHeader1 = TextStyle(
        color = Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    bodyHeader2 = TextStyle(
        color = Black,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    body1 = TextStyle(
        color = Black,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    body2 = TextStyle(
        color = Black,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Montserrat,
        textAlign = TextAlign.Left
    ),
    button = TextStyle(
        color = White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Left
    )
)