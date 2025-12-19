package com.ars.paulet.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Formas del tema Wabi-Sabi
 * 
 * Filosofía: Formas suaves y orgánicas
 * Bordes redondeados que evocan naturalidad
 */

val WabiShapes = Shapes(
    // Para elementos pequeños (chips, badges)
    extraSmall = RoundedCornerShape(4.dp),
    
    // Para botones y campos de texto
    small = RoundedCornerShape(8.dp),
    
    // Para cards y contenedores
    medium = RoundedCornerShape(12.dp),
    
    // Para diálogos y hojas modales
    large = RoundedCornerShape(16.dp),
    
    // Para contenedores grandes (bottom sheets)
    extraLarge = RoundedCornerShape(24.dp)
)

/**
 * Formas personalizadas adicionales
 */
object WabiCustomShapes {
    
    // Forma para botones de actividad
    val activityButton = RoundedCornerShape(16.dp)
    
    // Forma para tarjetas de lección
    val lessonCard = RoundedCornerShape(20.dp)
    
    // Forma para el reproductor de audio
    val audioPlayer = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
    
    // Forma para indicadores de progreso
    val progressIndicator = RoundedCornerShape(50)
    
    // Forma para avatares/imágenes
    val avatar = RoundedCornerShape(50)
    
    // Forma asimétrica (wabi-sabi)
    val wabiCard = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 8.dp
    )
}
