package com.ars.paulet.presentation.components.audio

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ars.paulet.domain.model.AudioState
import com.ars.paulet.domain.model.PlaybackSpeed
import com.ars.paulet.presentation.theme.WabiCustomShapes
import com.ars.paulet.presentation.theme.WabiDimensions

/**
 * Reproductor de audio personalizado con estética Wabi-Sabi
 * UI/UX premium para las lecciones de japonés
 */
@Composable
fun WabiAudioPlayer(
    audioState: AudioState,
    onPlayPause: () -> Unit,
    onSeek: (Long) -> Unit,
    onSpeedChange: (PlaybackSpeed) -> Unit,
    modifier: Modifier = Modifier
) {
    var showSpeedMenu by remember { mutableStateOf(false) }
    
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = WabiCustomShapes.audioPlayer,
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WabiDimensions.spacingMd)
        ) {
            // Barra de progreso
            AudioProgressBar(
                currentPosition = audioState.currentPosition,
                duration = audioState.duration,
                onSeek = onSeek
            )
            
            Spacer(modifier = Modifier.height(WabiDimensions.spacingSm))
            
            // Controles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tiempo actual
                Text(
                    text = formatTime(audioState.currentPosition),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                // Controles de reproducción
                Row(
                    horizontalArrangement = Arrangement.spacedBy(WabiDimensions.spacingSm),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Retroceder 10s
                    IconButton(
                        onClick = { 
                            onSeek(maxOf(0L, audioState.currentPosition - 10000L)) 
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Replay10,
                            contentDescription = "Retroceder 10 segundos"
                        )
                    }
                    
                    // Play/Pause
                    FilledIconButton(
                        onClick = onPlayPause,
                        modifier = Modifier.size(56.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        if (audioState.isBuffering) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = if (audioState.isPlaying) 
                                    Icons.Filled.Pause 
                                else 
                                    Icons.Filled.PlayArrow,
                                contentDescription = if (audioState.isPlaying) "Pausar" else "Reproducir",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                    
                    // Adelantar 10s
                    IconButton(
                        onClick = { 
                            onSeek(minOf(audioState.duration, audioState.currentPosition + 10000L)) 
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Forward10,
                            contentDescription = "Adelantar 10 segundos"
                        )
                    }
                }
                
                // Velocidad de reproducción
                Box {
                    TextButton(onClick = { showSpeedMenu = true }) {
                        Text(
                            text = "${audioState.playbackSpeed}x",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    
                    DropdownMenu(
                        expanded = showSpeedMenu,
                        onDismissRequest = { showSpeedMenu = false }
                    ) {
                        PlaybackSpeed.entries.forEach { speed ->
                            DropdownMenuItem(
                                text = { Text(speed.label) },
                                onClick = {
                                    onSpeedChange(speed)
                                    showSpeedMenu = false
                                },
                                leadingIcon = {
                                    if (audioState.playbackSpeed == speed.value) {
                                        Icon(
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AudioProgressBar(
    currentPosition: Long,
    duration: Long,
    onSeek: (Long) -> Unit
) {
    val progress = if (duration > 0) currentPosition.toFloat() / duration else 0f
    
    Slider(
        value = progress,
        onValueChange = { newProgress ->
            onSeek((newProgress * duration).toLong())
        },
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.primary,
            inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

/**
 * Reproductor compacto para listas
 */
@Composable
fun CompactAudioPlayer(
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    onPlayPause: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(WabiDimensions.spacingSm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPlayPause,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                contentDescription = if (isPlaying) "Pausar" else "Reproducir"
            )
        }
        
        Spacer(modifier = Modifier.width(WabiDimensions.spacingSm))
        
        LinearProgressIndicator(
            progress = { if (duration > 0) currentPosition.toFloat() / duration else 0f },
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.width(WabiDimensions.spacingSm))
        
        Text(
            text = formatTime(duration - currentPosition),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Indicador de ondas de audio animado
 */
@Composable
fun AudioWaveIndicator(
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            val delay = index * 100
            val height by infiniteTransition.animateFloat(
                initialValue = 8f,
                targetValue = if (isPlaying) 24f else 8f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = delay,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "waveHeight$index"
            )
            
            Surface(
                modifier = Modifier
                    .width(4.dp)
                    .height(height.dp),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary
            ) {}
        }
    }
}

// Función de utilidad para formatear tiempo
private fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}
