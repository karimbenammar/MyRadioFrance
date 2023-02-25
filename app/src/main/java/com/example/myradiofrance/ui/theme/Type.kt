package com.example.myradiofrance.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myradiofrance.R

val Golos = FontFamily(
    Font(R.font.golos_regular),
    Font(R.font.golos_medium, FontWeight.Medium),
    Font(R.font.golos_semibold, FontWeight.SemiBold),
    Font(R.font.golos_bold, FontWeight.Bold),
    Font(R.font.golos_extrabold, FontWeight.ExtraBold),
    Font(R.font.golos_black, FontWeight.Black),
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = Golos,
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        letterSpacing = 0.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
