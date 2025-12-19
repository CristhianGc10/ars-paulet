package com.ars.paulet.presentation.screens.lesson

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ars.paulet.domain.model.Activity
import com.ars.paulet.domain.model.ActivityContent
import com.ars.paulet.domain.model.ActivityType
import com.ars.paulet.presentation.theme.*

/**
 * Pantalla de Lección
 * Muestra las 4 actividades: Audición, Conversación, Lectura, Escritura
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    lessonId: String,
    onActivityClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    // TODO: Conectar con ViewModel
    val activities = remember { getSampleActivities(lessonId) }
    val lessonNumber = lessonId.substringAfterLast("_").toIntOrNull() ?: 1
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Lección $lessonNumber",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "第${lessonNumber}課",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(WabiDimensions.spacingMd),
            verticalArrangement = Arrangement.spacedBy(WabiDimensions.spacingMd)
        ) {
            // Progreso de la lección
            item {
                LessonProgressHeader(
                    completedActivities = activities.count { it.isCompleted },
                    totalActivities = activities.size
                )
            }
            
            // Lista de actividades
            items(activities) { activity ->
                ActivityCard(
                    activity = activity,
                    onClick = { onActivityClick(activity.id) }
                )
            }
        }
    }
}

@Composable
private fun LessonProgressHeader(
    completedActivities: Int,
    totalActivities: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Progreso",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "$completedActivities / $totalActivities",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(WabiDimensions.spacingSm))
            LinearProgressIndicator(
                progress = { completedActivities.toFloat() / totalActivities },
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surface
            )
        }
    }
}

@Composable
private fun ActivityCard(
    activity: Activity,
    onClick: () -> Unit
) {
    val (icon, color, description) = getActivityInfo(activity.type)
    
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingMd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono de actividad
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = color.copy(alpha = 0.15f),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(WabiDimensions.spacingMd))
            
            // Información
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = activity.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = activity.titleJapanese,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Estado
            if (activity.isCompleted) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Completado",
                    tint = WabiSuccess
                )
            }
        }
    }
}

private fun getActivityInfo(type: ActivityType): Triple<ImageVector, androidx.compose.ui.graphics.Color, String> {
    return when (type) {
        ActivityType.AUDICION -> Triple(
            Icons.Outlined.Headphones,
            ActivityListeningColor,
            "Comprensión auditiva"
        )
        ActivityType.CONVERSACION -> Triple(
            Icons.Outlined.RecordVoiceOver,
            ActivityConversationColor,
            "Práctica de conversación"
        )
        ActivityType.LECTURA -> Triple(
            Icons.Outlined.MenuBook,
            ActivityReadingColor,
            "Comprensión de lectura"
        )
        ActivityType.ESCRITURA -> Triple(
            Icons.Outlined.Edit,
            ActivityWritingColor,
            "Práctica de escritura"
        )
    }
}

// Datos de ejemplo
private fun getSampleActivities(lessonId: String): List<Activity> {
    return listOf(
        Activity(
            id = "${lessonId}_audicion",
            lessonId = lessonId,
            type = ActivityType.AUDICION,
            title = "Audición",
            titleJapanese = "聴解",
            content = ActivityContent.Listening(audioUrl = ""),
            isCompleted = false,
            order = 1
        ),
        Activity(
            id = "${lessonId}_conversacion",
            lessonId = lessonId,
            type = ActivityType.CONVERSACION,
            title = "Conversación",
            titleJapanese = "会話",
            content = ActivityContent.Conversation(),
            isCompleted = false,
            order = 2
        ),
        Activity(
            id = "${lessonId}_lectura",
            lessonId = lessonId,
            type = ActivityType.LECTURA,
            title = "Lectura",
            titleJapanese = "読解",
            content = ActivityContent.Reading(text = "", textJapanese = ""),
            isCompleted = false,
            order = 3
        ),
        Activity(
            id = "${lessonId}_escritura",
            lessonId = lessonId,
            type = ActivityType.ESCRITURA,
            title = "Escritura",
            titleJapanese = "作文",
            content = ActivityContent.Writing(prompt = "", promptJapanese = ""),
            isCompleted = false,
            order = 4
        )
    )
}
