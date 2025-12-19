package com.ars.paulet.presentation.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ars.paulet.presentation.theme.WabiDimensions

/**
 * Pantalla de Perfil del Usuario
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit
) {
    // TODO: Conectar con ViewModel
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi Perfil",
                        style = MaterialTheme.typography.titleLarge
                    )
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
            // Avatar y nombre
            item {
                ProfileHeader()
            }
            
            // Estadísticas
            item {
                StatsSection()
            }
            
            // Logros/Racha
            item {
                StreakSection()
            }
        }
    }
}

@Composable
private fun ProfileHeader() {
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
            // Avatar placeholder
            Surface(
                modifier = Modifier.size(80.dp),
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(WabiDimensions.spacingMd))
            
            Text(
                text = "Estudiante",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Text(
                text = "学習者",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun StatsSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingMd)
        ) {
            Text(
                text = "Estadísticas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = WabiDimensions.spacingMd)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(
                    icon = Icons.Outlined.School,
                    value = "0",
                    label = "Lecciones"
                )
                StatItem(
                    icon = Icons.Outlined.Timer,
                    value = "0h",
                    label = "Tiempo"
                )
                StatItem(
                    icon = Icons.Outlined.Star,
                    value = "0%",
                    label = "Promedio"
                )
            }
        }
    }
}

@Composable
private fun StatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(WabiDimensions.spacingXs))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StreakSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingMd),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.LocalFireDepartment,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )
            
            Spacer(modifier = Modifier.height(WabiDimensions.spacingSm))
            
            Text(
                text = "0 días de racha",
                style = MaterialTheme.typography.titleMedium
            )
            
            Text(
                text = "¡Comienza tu racha de estudio!",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
