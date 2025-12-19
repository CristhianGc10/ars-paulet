package com.ars.paulet.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ars.paulet.R

/**
 * Tipografía del tema Wabi-Sabi
 * 
 * Fuente principal: Noto Sans JP
 * - Elegante para texto japonés
 * - Clara y legible para texto en español
 * - Perfecta armonía entre ambos idiomas
 */

// Familia de fuentes Noto Sans JP
val NotoSansJP = FontFamily(
    Font(R.font.noto_sans_jp_thin, FontWeight.Thin),
    Font(R.font.noto_sans_jp_light, FontWeight.Light),
    Font(R.font.noto_sans_jp_regular, FontWeight.Normal),
    Font(R.font.noto_sans_jp_medium, FontWeight.Medium),
    Font(R.font.noto_sans_jp_bold, FontWeight.Bold),
    Font(R.font.noto_sans_jp_black, FontWeight.Black)
)

// Tipografía Material 3 personalizada
val WabiTypography = Typography(
    
    // Display - Para títulos grandes y destacados
    displayLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Light,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Light,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    
    // Headline - Para encabezados de sección
    headlineLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    
    // Title - Para títulos de cards y elementos
    titleLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    
    // Body - Para texto de contenido
    bodyLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    
    // Label - Para etiquetas y botones
    labelLarge = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

/**
 * Estilos de texto adicionales para contenido japonés
 */
object JapaneseTextStyles {
    
    // Texto japonés grande (para títulos de lección)
    val japaneseTitle = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
    
    // Furigana (texto pequeño sobre kanji)
    val furigana = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp
    )
    
    // Texto de lectura (hiragana/katakana)
    val reading = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
    
    // Texto de diálogo
    val dialogue = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 26.sp
    )
    
    // Vocabulario
    val vocabulary = TextStyle(
        fontFamily = NotoSansJP,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    )
}
