package com.ars.paulet.domain.model

/**
 * Estado del reproductor de audio
 * Para las actividades de audición y conversación
 */
data class AudioState(
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false,
    val isBuffering: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val playbackSpeed: Float = 1.0f,
    val volume: Float = 1.0f,
    val currentTrackId: String? = null,
    val error: AudioError? = null
)

/**
 * Errores posibles del reproductor de audio
 */
sealed class AudioError {
    data object NetworkError : AudioError()
    data object FileNotFound : AudioError()
    data object PlaybackError : AudioError()
    data class Unknown(val message: String) : AudioError()
}

/**
 * Pista de audio con metadata
 */
data class AudioTrack(
    val id: String,
    val url: String,
    val title: String,
    val titleJapanese: String? = null,
    val duration: Long = 0L,
    val lessonId: String? = null,
    val activityId: String? = null
)

/**
 * Velocidades de reproducción disponibles
 */
enum class PlaybackSpeed(val value: Float, val label: String) {
    SLOW(0.75f, "0.75x"),
    NORMAL(1.0f, "1x"),
    FAST(1.25f, "1.25x"),
    FASTER(1.5f, "1.5x")
}
