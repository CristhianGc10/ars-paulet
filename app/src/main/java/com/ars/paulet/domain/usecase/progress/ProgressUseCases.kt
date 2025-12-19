package com.ars.paulet.domain.usecase.progress

import com.ars.paulet.domain.model.ProgressStatus
import com.ars.paulet.domain.model.UserProgress
import com.ars.paulet.domain.model.UserStats
import com.ars.paulet.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * Obtener estadísticas del usuario
 */
class GetUserStatsUseCase(
    private val repository: ProgressRepository
) {
    operator fun invoke(): Flow<UserStats> {
        return repository.getUserStats()
    }
}

/**
 * Obtener progreso por curso
 */
class GetCourseProgressUseCase(
    private val repository: ProgressRepository
) {
    operator fun invoke(courseId: String): Flow<List<UserProgress>> {
        return repository.getProgressByCourse(courseId)
    }
}

/**
 * Guardar progreso de una lección
 */
class SaveLessonProgressUseCase(
    private val repository: ProgressRepository
) {
    suspend operator fun invoke(
        courseId: String,
        lessonId: String,
        activityId: String? = null,
        score: Int = 0,
        status: ProgressStatus = ProgressStatus.IN_PROGRESS
    ) {
        val progress = UserProgress(
            id = "${courseId}_${lessonId}_${activityId ?: "main"}",
            courseId = courseId,
            lessonId = lessonId,
            activityId = activityId,
            status = status,
            score = score,
            completedAt = if (status == ProgressStatus.COMPLETED) LocalDateTime.now() else null
        )
        repository.saveProgress(progress)
    }
}

/**
 * Actualizar tiempo de estudio
 */
class UpdateStudyTimeUseCase(
    private val repository: ProgressRepository
) {
    suspend operator fun invoke(
        progress: UserProgress,
        additionalSeconds: Long
    ) {
        repository.updateProgress(
            progress.copy(
                timeSpentSeconds = progress.timeSpentSeconds + additionalSeconds,
                lastAccessedAt = LocalDateTime.now()
            )
        )
    }
}

/**
 * Reiniciar progreso de un curso
 */
class ResetCourseProgressUseCase(
    private val repository: ProgressRepository
) {
    suspend operator fun invoke(courseId: String) {
        repository.resetProgress(courseId)
    }
}
