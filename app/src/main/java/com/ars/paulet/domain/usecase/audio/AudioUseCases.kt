package com.ars.paulet.domain.usecase.audio

import com.ars.paulet.domain.model.AudioState
import com.ars.paulet.domain.model.AudioTrack
import com.ars.paulet.domain.model.PlaybackSpeed
import kotlinx.coroutines.flow.Flow

/**
 * Interface para el controlador de audio
 * Implementado en la capa de Data con ExoPlayer
 */
interface AudioController {
    val audioState: Flow<AudioState>
    
    fun play(track: AudioTrack)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(position: Long)
    fun setPlaybackSpeed(speed: PlaybackSpeed)
    fun setVolume(volume: Float)
    fun release()
}

/**
 * Reproducir una pista de audio
 */
class PlayAudioUseCase(
    private val audioController: AudioController
) {
    operator fun invoke(track: AudioTrack) {
        audioController.play(track)
    }
}

/**
 * Pausar reproducción
 */
class PauseAudioUseCase(
    private val audioController: AudioController
) {
    operator fun invoke() {
        audioController.pause()
    }
}

/**
 * Reanudar reproducción
 */
class ResumeAudioUseCase(
    private val audioController: AudioController
) {
    operator fun invoke() {
        audioController.resume()
    }
}

/**
 * Detener reproducción
 */
class StopAudioUseCase(
    private val audioController: AudioController
) {
    operator fun invoke() {
        audioController.stop()
    }
}

/**
 * Saltar a posición específica
 */
class SeekAudioUseCase(
    private val audioController: AudioController
) {
    operator fun invoke(position: Long) {
        audioController.seekTo(position)
    }
}

/**
 * Cambiar velocidad de reproducción
 */
class SetPlaybackSpeedUseCase(
    private val audioController: AudioController
) {
    operator fun invoke(speed: PlaybackSpeed) {
        audioController.setPlaybackSpeed(speed)
    }
}

/**
 * Observar estado del audio
 */
class ObserveAudioStateUseCase(
    private val audioController: AudioController
) {
    operator fun invoke(): Flow<AudioState> {
        return audioController.audioState
    }
}
