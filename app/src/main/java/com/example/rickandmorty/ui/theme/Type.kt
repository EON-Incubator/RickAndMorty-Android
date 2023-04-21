package com.example.rickandmorty.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R

val get_schwifty = FontFamily(Font(R.font.get_schwifty))
val creepster_regular = FontFamily(Font(R.font.creepster_regular))
val chalkboard_bold = FontFamily(Font(R.font.chalkboard_bold))
val chalkboard_light = FontFamily(Font(R.font.chalkboard_light))
val chalkboard_regular = FontFamily(Font(R.font.chalkboard_regular))

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = get_schwifty,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 25.sp
    ),

    h2 = TextStyle(
        fontFamily = creepster_regular,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 21.sp
    ),

    body1 = TextStyle(
        fontFamily = chalkboard_bold,
        fontSize = 15.sp
    ),

    body2 = TextStyle(
        fontFamily = chalkboard_regular,
        fontSize = 13.sp
    )

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