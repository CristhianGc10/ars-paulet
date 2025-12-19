package com.ars.paulet.presentation.components.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.ars.paulet.domain.model.Course
import com.ars.paulet.domain.model.CourseLevel
import com.ars.paulet.presentation.theme.*

/**
 * Tarjeta de Curso para la pantalla principal
 */
@Composable
fun CourseCard(
    course: Course,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val levelColor = when (course.level) {
        CourseLevel.INICIAL -> LevelInicialColor
        CourseLevel.BASICO_1 -> LevelBasico1Color
        CourseLevel.BASICO_2 -> LevelBasico2Color
    }
    
    val containerColor by animateColorAsState(
        targetValue = if (course.isUnlocked) 
            MaterialTheme.colorScheme.surface 
        else 
            MaterialTheme.colorScheme.surfaceVariant,
        label = "containerColor"
    )
    
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (course.isUnlocked) 1f else 0.6f),
        enabled = course.isUnlocked,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingMd)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Indicador de nivel
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = levelColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = course.nameJapanese,
                        style = MaterialTheme.typography.labelMedium,
                        color = levelColor,
                        modifier = Modifier.padding(
                            horizontal = WabiDimensions.spacingSm,
                            vertical = WabiDimensions.spacingXs
                        )
                    )
                }
                
                // Candado si está bloqueado
                if (!course.isUnlocked) {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Bloqueado",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(WabiDimensions.spacingMd))
            
            // Título
            Text(
                text = course.name,
                style = MaterialTheme.typography.titleLarge
            )
            
            Text(
                text = course.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(WabiDimensions.spacingMd))
            
            // Progreso
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${course.completedLessons}/${course.totalLessons} lecciones",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                // Barra de progreso circular pequeña
                val progress = if (course.totalLessons > 0) 
                    course.completedLessons.toFloat() / course.totalLessons 
                else 0f
                
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 3.dp,
                    color = levelColor,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

/**
 * Indicador de progreso lineal estilizado
 */
@Composable
fun WabiProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp),
        color = ProgressFilledColor,
        trackColor = ProgressEmptyColor,
        strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
    )
}

/**
 * Botón primario estilizado
 */
@Composable
fun WabiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(WabiDimensions.buttonHeight),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/**
 * Botón secundario (outline)
 */
@Composable
fun WabiOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(WabiDimensions.buttonHeight),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
