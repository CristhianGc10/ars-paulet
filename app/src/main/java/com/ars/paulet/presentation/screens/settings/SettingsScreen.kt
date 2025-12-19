package com.ars.paulet.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ars.paulet.presentation.theme.WabiDimensions

/**
 * Pantalla de Configuración
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Configuración",
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
            contentPadding = PaddingValues(WabiDimensions.spacingMd)
        ) {
            // Sección: Apariencia
            item {
                SettingsSection(title = "Apariencia")
            }
            
            item {
                SettingsSwitchItem(
                    icon = Icons.Outlined.DarkMode,
                    title = "Modo oscuro",
                    subtitle = "Activar tema oscuro",
                    checked = darkModeEnabled,
                    onCheckedChange = { darkModeEnabled = it }
                )
            }
            
            // Sección: Notificaciones
            item {
                SettingsSection(title = "Notificaciones")
            }
            
            item {
                SettingsSwitchItem(
                    icon = Icons.Outlined.Notifications,
                    title = "Notificaciones",
                    subtitle = "Recordatorios de estudio",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }
            
            // Sección: Audio
            item {
                SettingsSection(title = "Audio")
            }
            
            item {
                SettingsSwitchItem(
                    icon = Icons.Outlined.VolumeUp,
                    title = "Sonidos",
                    subtitle = "Efectos de sonido en la app",
                    checked = soundEnabled,
                    onCheckedChange = { soundEnabled = it }
                )
            }
            
            // Sección: Información
            item {
                SettingsSection(title = "Información")
            }
            
            item {
                SettingsClickItem(
                    icon = Icons.Outlined.Info,
                    title = "Acerca de",
                    subtitle = "Versión 1.0.0",
                    onClick = { /* TODO */ }
                )
            }
            
            item {
                SettingsClickItem(
                    icon = Icons.Outlined.Policy,
                    title = "Privacidad",
                    subtitle = "Política de privacidad",
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(
            top = WabiDimensions.spacingLg,
            bottom = WabiDimensions.spacingSm
        )
    )
}

@Composable
private fun SettingsSwitchItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onCheckedChange(!checked) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = WabiDimensions.spacingSm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(WabiDimensions.spacingMd))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
private fun SettingsClickItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = WabiDimensions.spacingSm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(WabiDimensions.spacingMd))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
