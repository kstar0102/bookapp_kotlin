package com.books.app.theme


import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.books.app.R

val  georgiaFontFamily = FontFamily(
    Font(R.font.georgia_italic),
    Font(R.font.georgia, style = androidx.compose.ui.text.font.FontStyle.Italic)
)

val nunitoFontFamily = FontFamily(
    Font(R.font.nunito_sans)
)

val Typography = Typography(

    h1 = TextStyle(
        fontSize = 52.sp,
        fontWeight = FontWeight.Bold,
        color = appPink,
        fontFamily = georgiaFontFamily,
    ),

    h5 = TextStyle(
        fontSize = 24.sp,
        color = Color.White,
        fontWeight = FontWeight(700),
        fontFamily = nunitoFontFamily,
    ),

    h6 = TextStyle(
        fontSize = 21.sp,
        color = appPink,
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Bold,
    ),

    body1 = TextStyle(
        color = Color.White,
        fontSize = 18.sp,
    ),

    body2 = TextStyle(
        color = Color.Black,
        fontSize = 16.sp,
    )
)
