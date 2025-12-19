package com.ars.paulet.data.service

import android.content.Context
import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.ars.paulet.domain.model.*
import com.ars.paulet.domain.usecase.audio.AudioController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Servicio de reproducción de audio con ExoPlayer
 * Implementa UI/UX personalizado para las lecciones de japonés
 */
@OptIn(UnstableApi::class)
class AudioPlaybackService : MediaSessionService() {
    
    private var mediaSession: MediaSession? = null
    private lateinit var player: ExoPlayer
    
    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        mediaSession = MediaSession.Builder(this, player).build()
    }
    
    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }
    
    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }
}

/**
 * Controlador de audio que implementa la interfaz del dominio
 * Utiliza ExoPlayer para reproducción con UI personalizada
 */
class AudioControllerImpl(
    private val context: Context
) : AudioController {
    
    private val player: ExoPlayer = ExoPlayer.Builder(context).build()
    
    private val _audioState = MutableStateFlow(AudioState())
    override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()
    
    private var currentTrack: AudioTrack? = null
    private var positionUpdateJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)
    
    init {
        setupPlayerListener()
    }
    
    private fun setupPlayerListener() {
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                updateState {
                    copy(
                        isBuffering = playbackState == Player.STATE_BUFFERING,
                        isPlaying = playbackState == Player.STATE_READY && player.isPlaying
                    )
                }
                
                if (playbackState == Player.STATE_READY) {
                    updateState { copy(duration = player.duration) }
                    startPositionUpdates()
                }
            }
            
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updateState {
                    copy(
                        isPlaying = isPlaying,
                        isPaused = !isPlaying && player.playbackState == Player.STATE_READY
                    )
                }
                
                if (isPlaying) {
                    startPositionUpdates()
                } else {
                    stopPositionUpdates()
                }
            }
            
            override fun onPlayerError(error: PlaybackException) {
                val audioError = when (error.errorCode) {
                    PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> AudioError.NetworkError
                    PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND -> AudioError.FileNotFound
                    else -> AudioError.Unknown(error.message ?: "Unknown error")
                }
                updateState { copy(error = audioError, isPlaying = false) }
            }
        })
    }
    
    private fun startPositionUpdates() {
        positionUpdateJob?.cancel()
        positionUpdateJob = scope.launch {
            while (true) {
                updateState { copy(currentPosition = player.currentPosition) }
                delay(100) // Actualizar cada 100ms para animaciones fluidas
            }
        }
    }
    
    private fun stopPositionUpdates() {
        positionUpdateJob?.cancel()
        positionUpdateJob = null
    }
    
    override fun play(track: AudioTrack) {
        currentTrack = track
        updateState {
            AudioState(
                currentTrackId = track.id,
                isBuffering = true
            )
        }
        
        val mediaItem = MediaItem.fromUri(track.url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
    
    override fun pause() {
        player.pause()
    }
    
    override fun resume() {
        player.play()
    }
    
    override fun stop() {
        player.stop()
        stopPositionUpdates()
        updateState { AudioState() }
    }
    
    override fun seekTo(position: Long) {
        player.seekTo(position)
        updateState { copy(currentPosition = position) }
    }
    
    override fun setPlaybackSpeed(speed: PlaybackSpeed) {
        player.setPlaybackSpeed(speed.value)
        updateState { copy(playbackSpeed = speed.value) }
    }
    
    override fun setVolume(volume: Float) {
        player.volume = volume.coerceIn(0f, 1f)
        updateState { copy(volume = volume) }
    }
    
    override fun release() {
        stopPositionUpdates()
        player.release()
    }
    
    private inline fun updateState(update: AudioState.() -> AudioState) {
        _audioState.value = _audioState.value.update()
    }
}
