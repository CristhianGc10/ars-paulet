package com.ars.paulet.domain.model

import java.time.LocalDateTime

/**
 * Representa el progreso del usuario en la aplicación
 */
data class UserProgress(
    val id: String,
    val courseId: String,
    val lessonId: String,
    val activityId: String? = null,
    val status: ProgressStatus = ProgressStatus.NOT_STARTED,
    val score: Int = 0,
    val maxScore: Int = 100,
    val completedAt: LocalDateTime? = null,
    val lastAccessedAt: LocalDateTime = LocalDateTime.now(),
    val timeSpentSeconds: Long = 0
)

enum class ProgressStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED
}

/**
 * Estadísticas generales del usuario
 */
data class UserStats(
    val totalLessonsCompleted: Int = 0,
    val totalActivitiesCompleted: Int = 0,
    val totalTimeSpentMinutes: Long = 0,
    val averageScore: Float = 0f,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val lastStudyDate: LocalDateTime? = null
)

/**
 * Configuración y perfil del usuario
 */
data class UserProfile(
    val id: String,
    val displayName: String? = null,
    val preferredLanguage: String = "es", // español por defecto
    val dailyGoalMinutes: Int = 15,
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false,
    val soundEnabled: Boolean = true,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
