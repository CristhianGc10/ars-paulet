package com.ars.paulet.domain.usecase.lesson

import com.ars.paulet.domain.model.Activity
import com.ars.paulet.domain.model.Lesson
import com.ars.paulet.domain.repository.LessonRepository
import kotlinx.coroutines.flow.Flow

/**
 * Obtener todas las lecciones de un curso
 */
class GetLessonsByCourseUseCase(
    private val repository: LessonRepository
) {
    operator fun invoke(courseId: String): Flow<List<Lesson>> {
        return repository.getLessonsByCourse(courseId)
    }
}

/**
 * Obtener una lección específica por ID
 */
class GetLessonByIdUseCase(
    private val repository: LessonRepository
) {
    operator fun invoke(lessonId: String): Flow<Lesson?> {
        return repository.getLessonById(lessonId)
    }
}

/**
 * Obtener las actividades de una lección
 */
class GetActivitiesByLessonUseCase(
    private val repository: LessonRepository
) {
    operator fun invoke(lessonId: String): Flow<List<Activity>> {
        return repository.getActivitiesByLesson(lessonId)
    }
}

/**
 * Marcar una actividad como completada
 */
class CompleteActivityUseCase(
    private val repository: LessonRepository
) {
    suspend operator fun invoke(activity: Activity) {
        repository.updateActivity(activity.copy(isCompleted = true))
    }
}

/**
 * Marcar una lección como completada
 */
class CompleteLessonUseCase(
    private val repository: LessonRepository
) {
    suspend operator fun invoke(lesson: Lesson) {
        repository.updateLesson(lesson.copy(isCompleted = true, progress = 1f))
    }
}
