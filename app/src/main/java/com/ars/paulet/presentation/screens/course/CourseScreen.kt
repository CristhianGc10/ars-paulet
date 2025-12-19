package com.ars.paulet.presentation.screens.course

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ars.paulet.domain.model.Lesson
import com.ars.paulet.presentation.components.lesson.LessonCard
import com.ars.paulet.presentation.theme.WabiDimensions

/**
 * Pantalla de Curso
 * Muestra las 18 lecciones del curso seleccionado
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(
    courseId: String,
    onLessonClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    // TODO: Conectar con ViewModel
    val lessons = remember { getSampleLessons(courseId) }
    val courseName = when (courseId) {
        "inicial" -> "Nivel Inicial"
        "basico1" -> "Básico 1"
        "basico2" -> "Básico 2"
        else -> "Curso"
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = courseName,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "${lessons.size} lecciones",
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(WabiDimensions.spacingMd),
            horizontalArrangement = Arrangement.spacedBy(WabiDimensions.spacingSm),
            verticalArrangement = Arrangement.spacedBy(WabiDimensions.spacingSm)
        ) {
            items(lessons) { lesson ->
                LessonCard(
                    lesson = lesson,
                    onClick = { onLessonClick(lesson.id) }
                )
            }
        }
    }
}

// Datos de ejemplo
private fun getSampleLessons(courseId: String): List<Lesson> {
    return (1..18).map { number ->
        Lesson(
            id = "${courseId}_lesson_$number",
            courseId = courseId,
            number = number,
            title = "Lección $number",
            titleJapanese = "第${number}課",
            description = "Contenido de la lección $number",
            isCompleted = false,
            isUnlocked = number == 1,
            progress = 0f
        )
    }
}
