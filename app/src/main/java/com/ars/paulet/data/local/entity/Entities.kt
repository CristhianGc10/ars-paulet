package com.ars.paulet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Entidad de Curso para Room Database
 */
@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val nameJapanese: String,
    val description: String,
    val level: String, // CourseLevel como String
    val totalLessons: Int,
    val completedLessons: Int,
    val imageUrl: String?,
    val isUnlocked: Boolean
)

/**
 * Entidad de Lección para Room Database
 */
@Entity(
    tableName = "lessons",
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("courseId")]
)
data class LessonEntity(
    @PrimaryKey
    val id: String,
    val courseId: String,
    val number: Int,
    val title: String,
    val titleJapanese: String,
    val description: String,
    val isCompleted: Boolean,
    val isUnlocked: Boolean,
    val progress: Float,
    val imageUrl: String?
)

/**
 * Entidad de Actividad para Room Database
 */
@Entity(
    tableName = "activities",
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("lessonId")]
)
data class ActivityEntity(
    @PrimaryKey
    val id: String,
    val lessonId: String,
    val type: String, // ActivityType como String
    val title: String,
    val titleJapanese: String,
    val contentJson: String, // ActivityContent serializado como JSON
    val isCompleted: Boolean,
    val order: Int
)

/**
 * Entidad de Progreso del Usuario
 */
@Entity(
    tableName = "user_progress",
    indices = [
        Index("courseId"),
        Index("lessonId")
    ]
)
data class UserProgressEntity(
    @PrimaryKey
    val id: String,
    val courseId: String,
    val lessonId: String,
    val activityId: String?,
    val status: String, // ProgressStatus como String
    val score: Int,
    val maxScore: Int,
    val completedAt: Long?, // Timestamp
    val lastAccessedAt: Long,
    val timeSpentSeconds: Long
)

/**
 * Entidad de Perfil de Usuario
 */
@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: String,
    val displayName: String?,
    val preferredLanguage: String,
    val dailyGoalMinutes: Int,
    val notificationsEnabled: Boolean,
    val darkModeEnabled: Boolean,
    val soundEnabled: Boolean,
    val createdAt: Long // Timestamp
)

/**
 * Entidad de Vocabulario (para búsqueda y repaso)
 */
@Entity(
    tableName = "vocabulary",
    indices = [Index("lessonId")]
)
data class VocabularyEntity(
    @PrimaryKey
    val id: String,
    val lessonId: String,
    val word: String,
    val reading: String,
    val meaning: String,
    val example: String?,
    val exampleMeaning: String?,
    val audioUrl: String?,
    val isFavorite: Boolean = false,
    val timesReviewed: Int = 0
)
