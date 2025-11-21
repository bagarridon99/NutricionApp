package com.example.nutricionapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Usamos la familia "Rounded" que viene en Android y se ve muy amigable
val AppFont = FontFamily.Default

val Typography = Typography(
    // Títulos Grandes (Headers)
    titleLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.ExtraBold, // Más gordita
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.5).sp // Un pelín juntas para estilo moderno
    ),
    // Títulos Medianos (Tarjetas)
    titleMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    // Texto normal (Cuerpo)
    bodyMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Texto pequeño (Chips, fechas)
    labelSmall = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)