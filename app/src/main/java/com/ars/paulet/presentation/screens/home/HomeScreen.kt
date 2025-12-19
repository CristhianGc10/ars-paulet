package com.ars.paulet.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ars.paulet.domain.model.Course
import com.ars.paulet.presentation.components.common.CourseCard
import com.ars.paulet.presentation.theme.WabiDimensions

/**
 * Pantalla principal - Home
 * Muestra los tres cursos: Inicial, Básico 1, Básico 2
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCourseClick: (String) -> Unit,
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    // TODO: Conectar con ViewModel
    val courses = remember { getSampleCourses() }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Ars Paulet",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "いろどり - Japonés para la vida",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Perfil"
                        )
                    }
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Configuración"
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
            // Header de bienvenida
            item {
                WelcomeHeader()
            }
            
            // Lista de cursos
            item {
                Text(
                    text = "Tus Cursos",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = WabiDimensions.spacingSm)
                )
            }
            
            items(courses) { course ->
                CourseCard(
                    course = course,
                    onClick = { onCourseClick(course.id) }
                )
            }
            
            // Espacio al final
            item {
                Spacer(modifier = Modifier.height(WabiDimensions.spacingXl))
            }
        }
    }
}

@Composable
private fun WelcomeHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingLg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ようこそ",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(WabiDimensions.spacingSm))
            Text(
                text = "Bienvenido a tu viaje de aprendizaje",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Datos de ejemplo - Reemplazar con datos reales
private fun getSampleCourses(): List<Course> {
    return listOf(
        Course(
            id = "inicial",
            name = "Nivel Inicial",
            nameJapanese = "入門",
            description = "Primeros pasos en japonés",
            level = com.ars.paulet.domain.model.CourseLevel.INICIAL,
            totalLessons = 18,
            completedLessons = 0,
            isUnlocked = true
        ),
        Course(
            id = "basico1",
            name = "Básico 1",
            nameJapanese = "初級1",
            description = "Fundamentos del idioma",
            level = com.ars.paulet.domain.model.CourseLevel.BASICO_1,
            totalLessons = 18,
            completedLessons = 0,
            isUnlocked = false
        ),
        Course(
            id = "basico2",
            name = "Básico 2",
            nameJapanese = "初級2",
            description = "Comunicación cotidiana",
            level = com.ars.paulet.domain.model.CourseLevel.BASICO_2,
            totalLessons = 18,
            completedLessons = 0,
            isUnlocked = false
        )
    )
}
