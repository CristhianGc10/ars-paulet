package com.ars.paulet.data.repository

import com.ars.paulet.data.local.dao.*
import com.ars.paulet.data.mapper.*
import com.ars.paulet.domain.model.*
import com.ars.paulet.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación del repositorio de Cursos
 */
class CourseRepositoryImpl(
    private val courseDao: CourseDao
) : CourseRepository {
    
    override fun getAllCourses(): Flow<List<Course>> {
        return courseDao.getAllCourses().map { it.toDomainCourses() }
    }
    
    override fun getCourseById(id: String): Flow<Course?> {
        return courseDao.getCourseById(id).map { it?.toDomain() }
    }
    
    override fun getCoursesByLevel(level: CourseLevel): Flow<List<Course>> {
        return courseDao.getCoursesByLevel(level.name).map { it.toDomainCourses() }
    }
    
    override suspend fun updateCourse(course: Course) {
        courseDao.updateCourse(course.toEntity())
    }
}

/**
 * Implementación del repositorio de Lecciones
 */
class LessonRepositoryImpl(
    private val lessonDao: LessonDao,
    private val activityDao: ActivityDao
) : LessonRepository {
    
    override fun getLessonsByCourse(courseId: String): Flow<List<Lesson>> {
        return lessonDao.getLessonsByCourse(courseId).map { it.toDomainLessons() }
    }
    
    override fun getLessonById(id: String): Flow<Lesson?> {
        return lessonDao.getLessonById(id).map { it?.toDomain() }
    }
    
    override fun getActivitiesByLesson(lessonId: String): Flow<List<Activity>> {
        return activityDao.getActivitiesByLesson(lessonId).map { it.toDomainActivities() }
    }
    
    override fun getActivityById(id: String): Flow<Activity?> {
        return activityDao.getActivityById(id).map { it?.toDomain() }
    }
    
    override suspend fun updateLesson(lesson: Lesson) {
        lessonDao.updateLesson(lesson.toEntity())
    }
    
    override suspend fun updateActivity(activity: Activity) {
        activityDao.updateActivity(activity.toEntity())
    }
}

/**
 * Implementación del repositorio de Progreso
 */
class ProgressRepositoryImpl(
    private val progressDao: ProgressDao
) : ProgressRepository {
    
    override fun getProgressByCourse(courseId: String): Flow<List<UserProgress>> {
        return progressDao.getProgressByCourse(courseId).map { it.toDomainProgress() }
    }
    
    override fun getProgressByLesson(lessonId: String): Flow<UserProgress?> {
        return progressDao.getProgressByLesson(lessonId).map { it?.toDomain() }
    }
    
    override fun getUserStats(): Flow<UserStats> {
        return progressDao.getUserStatsRaw().map { raw ->
            UserStats(
                totalLessonsCompleted = raw?.totalLessonsCompleted ?: 0,
                totalActivitiesCompleted = raw?.totalActivitiesCompleted ?: 0,
                totalTimeSpentMinutes = raw?.totalTimeSpentMinutes ?: 0,
                averageScore = raw?.averageScore ?: 0f
            )
        }
    }
    
    override suspend fun saveProgress(progress: UserProgress) {
        progressDao.insertProgress(progress.toEntity())
    }
    
    override suspend fun updateProgress(progress: UserProgress) {
        progressDao.updateProgress(progress.toEntity())
    }
    
    override suspend fun resetProgress(courseId: String) {
        progressDao.deleteProgressByCourse(courseId)
    }
}

/**
 * Implementación del repositorio de Usuario
 */
class UserRepositoryImpl(
    private val userProfileDao: UserProfileDao
) : UserRepository {
    
    override fun getUserProfile(): Flow<UserProfile?> {
        return userProfileDao.getUserProfile().map { it?.toDomain() }
    }
    
    override suspend fun saveUserProfile(profile: UserProfile) {
        userProfileDao.insertUserProfile(profile.toEntity())
    }
    
    override suspend fun updateUserProfile(profile: UserProfile) {
        userProfileDao.updateUserProfile(profile.toEntity())
    }
}
