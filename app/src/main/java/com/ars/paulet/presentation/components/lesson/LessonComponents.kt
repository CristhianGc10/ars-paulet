package com.ars.paulet.presentation.components.lesson

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ars.paulet.domain.model.Lesson
import com.ars.paulet.presentation.theme.WabiDimensions
import com.ars.paulet.presentation.theme.WabiSuccess

/**
 * Tarjeta de Lección para la vista de grid
 */
@Composable
fun LessonCard(
    lesson: Lesson,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .alpha(if (lesson.isUnlocked) 1f else 0.5f),
        enabled = lesson.isUnlocked,
        colors = CardDefaults.cardColors(
            containerColor = when {
                lesson.isCompleted -> MaterialTheme.colorScheme.primaryContainer
                lesson.isUnlocked -> MaterialTheme.colorScheme.surface
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WabiDimensions.spacingSm),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Número de lección
                Text(
                    text = lesson.number.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = when {
                        lesson.isCompleted -> MaterialTheme.colorScheme.onPrimaryContainer
                        lesson.isUnlocked -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
                
                // Título japonés
                Text(
                    text = lesson.titleJapanese,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(WabiDimensions.spacingXs))
                
                // Indicador de estado
                when {
                    lesson.isCompleted -> {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Completada",
                            modifier = Modifier.size(20.dp),
                            tint = WabiSuccess
                        )
                    }
                    !lesson.isUnlocked -> {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Bloqueada",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    lesson.progress > 0f -> {
                        // Progreso parcial
                        LinearProgressIndicator(
                            progress = { lesson.progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = WabiDimensions.spacingSm)
                                .height(4.dp),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
            }
        }
    }
}

/**
 * Tarjeta de lección expandida (para lista)
 */
@Composable
fun LessonListCard(
    lesson: Lesson,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (lesson.isUnlocked) 1f else 0.6f),
        enabled = lesson.isUnlocked
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingMd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Número
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = when {
                    lesson.isCompleted -> MaterialTheme.colorScheme.primaryContainer
                    lesson.isUnlocked -> MaterialTheme.colorScheme.surfaceVariant
                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                },
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = lesson.number.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = when {
                            lesson.isCompleted -> MaterialTheme.colorScheme.onPrimaryContainer
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(WabiDimensions.spacingMd))
            
            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = lesson.titleJapanese,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Estado
            when {
                lesson.isCompleted -> {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = "Completada",
                        tint = WabiSuccess
                    )
                }
                !lesson.isUnlocked -> {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Bloqueada",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
