package com.ars.paulet.data.mapper

import com.ars.paulet.data.local.entity.*
import com.ars.paulet.domain.model.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Mappers para convertir entre entidades de Room y modelos de dominio
 * Filosofía Wabi-Sabi: Funciones simples y directas
 */

// ==================== COURSE MAPPERS ====================

fun CourseEntity.toDomain(): Course {
    return Course(
        id = id,
        name = name,
        nameJapanese = nameJapanese,
        description = description,
        level = CourseLevel.valueOf(level),
        totalLessons = totalLessons,
        completedLessons = completedLessons,
        imageUrl = imageUrl,
        isUnlocked = isUnlocked
    )
}

fun Course.toEntity(): CourseEntity {
    return CourseEntity(
        id = id,
        name = name,
        nameJapanese = nameJapanese,
        description = description,
        level = level.name,
        totalLessons = totalLessons,
        completedLessons = completedLessons,
        imageUrl = imageUrl,
        isUnlocked = isUnlocked
    )
}

fun List<CourseEntity>.toDomainCourses(): List<Course> = map { it.toDomain() }

// ==================== LESSON MAPPERS ====================

fun LessonEntity.toDomain(): Lesson {
    return Lesson(
        id = id,
        courseId = courseId,
        number = number,
        title = title,
        titleJapanese = titleJapanese,
        description = description,
        isCompleted = isCompleted,
        isUnlocked = isUnlocked,
        progress = progress,
        imageUrl = imageUrl
    )
}

fun Lesson.toEntity(): LessonEntity {
    return LessonEntity(
        id = id,
        courseId = courseId,
        number = number,
        title = title,
        titleJapanese = titleJapanese,
        description = description,
        isCompleted = isCompleted,
        isUnlocked = isUnlocked,
        progress = progress,
        imageUrl = imageUrl
    )
}

fun List<LessonEntity>.toDomainLessons(): List<Lesson> = map { it.toDomain() }

// ==================== ACTIVITY MAPPERS ====================

fun ActivityEntity.toDomain(): Activity {
    // Por ahora retornamos una actividad básica
    // La deserialización del contenido JSON se implementará después
    return Activity(
        id = id,
        lessonId = lessonId,
        type = ActivityType.valueOf(type),
        title = title,
        titleJapanese = titleJapanese,
        content = ActivityContent.Listening(audioUrl = ""), // Placeholder
        isCompleted = isCompleted,
        order = order
    )
}

fun Activity.toEntity(): ActivityEntity {
    return ActivityEntity(
        id = id,
        lessonId = lessonId,
        type = type.name,
        title = title,
        titleJapanese = titleJapanese,
        contentJson = "{}", // Serialización JSON se implementará después
        isCompleted = isCompleted,
        order = order
    )
}

fun List<ActivityEntity>.toDomainActivities(): List<Activity> = map { it.toDomain() }

// ==================== PROGRESS MAPPERS ====================

fun UserProgressEntity.toDomain(): UserProgress {
    return UserProgress(
        id = id,
        courseId = courseId,
        lessonId = lessonId,
        activityId = activityId,
        status = ProgressStatus.valueOf(status),
        score = score,
        maxScore = maxScore,
        completedAt = completedAt?.toLocalDateTime(),
        lastAccessedAt = lastAccessedAt.toLocalDateTime(),
        timeSpentSeconds = timeSpentSeconds
    )
}

fun UserProgress.toEntity(): UserProgressEntity {
    return UserProgressEntity(
        id = id,
        courseId = courseId,
        lessonId = lessonId,
        activityId = activityId,
        status = status.name,
        score = score,
        maxScore = maxScore,
        completedAt = completedAt?.toTimestamp(),
        lastAccessedAt = lastAccessedAt.toTimestamp(),
        timeSpentSeconds = timeSpentSeconds
    )
}

fun List<UserProgressEntity>.toDomainProgress(): List<UserProgress> = map { it.toDomain() }

// ==================== USER PROFILE MAPPERS ====================

fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        displayName = displayName,
        preferredLanguage = preferredLanguage,
        dailyGoalMinutes = dailyGoalMinutes,
        notificationsEnabled = notificationsEnabled,
        darkModeEnabled = darkModeEnabled,
        soundEnabled = soundEnabled,
        createdAt = createdAt.toLocalDateTime()
    )
}

fun UserProfile.toEntity(): UserProfileEntity {
    return UserProfileEntity(
        id = id,
        displayName = displayName,
        preferredLanguage = preferredLanguage,
        dailyGoalMinutes = dailyGoalMinutes,
        notificationsEnabled = notificationsEnabled,
        darkModeEnabled = darkModeEnabled,
        soundEnabled = soundEnabled,
        createdAt = createdAt.toTimestamp()
    )
}

// ==================== VOCABULARY MAPPERS ====================

fun VocabularyEntity.toDomain(): VocabularyItem {
    return VocabularyItem(
        word = word,
        reading = reading,
        meaning = meaning,
        example = example,
        exampleMeaning = exampleMeaning,
        audioUrl = audioUrl
    )
}

fun List<VocabularyEntity>.toDomainVocabulary(): List<VocabularyItem> = map { it.toDomain() }

// ==================== UTILITY EXTENSIONS ====================

private fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(this),
        ZoneId.systemDefault()
    )
}

private fun LocalDateTime.toTimestamp(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
