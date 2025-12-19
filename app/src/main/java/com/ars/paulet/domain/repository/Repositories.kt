package com.ars.paulet.domain.repository

import com.ars.paulet.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para gestionar cursos
 */
interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    fun getCourseById(id: String): Flow<Course?>
    fun getCoursesByLevel(level: CourseLevel): Flow<List<Course>>
    suspend fun updateCourse(course: Course)
}

/**
 * Repositorio para gestionar lecciones
 */
interface LessonRepository {
    fun getLessonsByCourse(courseId: String): Flow<List<Lesson>>
    fun getLessonById(id: String): Flow<Lesson?>
    fun getActivitiesByLesson(lessonId: String): Flow<List<Activity>>
    fun getActivityById(id: String): Flow<Activity?>
    suspend fun updateLesson(lesson: Lesson)
    suspend fun updateActivity(activity: Activity)
}

/**
 * Repositorio para gestionar progreso del usuario
 */
interface ProgressRepository {
    fun getProgressByCourse(courseId: String): Flow<List<UserProgress>>
    fun getProgressByLesson(lessonId: String): Flow<UserProgress?>
    fun getUserStats(): Flow<UserStats>
    suspend fun saveProgress(progress: UserProgress)
    suspend fun updateProgress(progress: UserProgress)
    suspend fun resetProgress(courseId: String)
}

/**
 * Repositorio para gestionar perfil de usuario
 */
interface UserRepository {
    fun getUserProfile(): Flow<UserProfile?>
    suspend fun saveUserProfile(profile: UserProfile)
    suspend fun updateUserProfile(profile: UserProfile)
}
