package com.ars.paulet.data.local.dao

import androidx.room.*
import com.ars.paulet.data.local.entity.*
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operaciones de Cursos
 */
@Dao
interface CourseDao {
    
    @Query("SELECT * FROM courses ORDER BY level")
    fun getAllCourses(): Flow<List<CourseEntity>>
    
    @Query("SELECT * FROM courses WHERE id = :id")
    fun getCourseById(id: String): Flow<CourseEntity?>
    
    @Query("SELECT * FROM courses WHERE level = :level")
    fun getCoursesByLevel(level: String): Flow<List<CourseEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)
    
    @Update
    suspend fun updateCourse(course: CourseEntity)
    
    @Delete
    suspend fun deleteCourse(course: CourseEntity)
}

/**
 * DAO para operaciones de Lecciones
 */
@Dao
interface LessonDao {
    
    @Query("SELECT * FROM lessons WHERE courseId = :courseId ORDER BY number")
    fun getLessonsByCourse(courseId: String): Flow<List<LessonEntity>>
    
    @Query("SELECT * FROM lessons WHERE id = :id")
    fun getLessonById(id: String): Flow<LessonEntity?>
    
    @Query("SELECT COUNT(*) FROM lessons WHERE courseId = :courseId AND isCompleted = 1")
    fun getCompletedLessonsCount(courseId: String): Flow<Int>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)
    
    @Update
    suspend fun updateLesson(lesson: LessonEntity)
    
    @Query("UPDATE lessons SET isCompleted = :isCompleted, progress = :progress WHERE id = :lessonId")
    suspend fun updateLessonProgress(lessonId: String, isCompleted: Boolean, progress: Float)
}

/**
 * DAO para operaciones de Actividades
 */
@Dao
interface ActivityDao {
    
    @Query("SELECT * FROM activities WHERE lessonId = :lessonId ORDER BY `order`")
    fun getActivitiesByLesson(lessonId: String): Flow<List<ActivityEntity>>
    
    @Query("SELECT * FROM activities WHERE id = :id")
    fun getActivityById(id: String): Flow<ActivityEntity?>
    
    @Query("SELECT COUNT(*) FROM activities WHERE lessonId = :lessonId AND isCompleted = 1")
    fun getCompletedActivitiesCount(lessonId: String): Flow<Int>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: ActivityEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivities(activities: List<ActivityEntity>)
    
    @Update
    suspend fun updateActivity(activity: ActivityEntity)
    
    @Query("UPDATE activities SET isCompleted = :isCompleted WHERE id = :activityId")
    suspend fun updateActivityCompletion(activityId: String, isCompleted: Boolean)
}

/**
 * DAO para operaciones de Progreso
 */
@Dao
interface ProgressDao {
    
    @Query("SELECT * FROM user_progress WHERE courseId = :courseId")
    fun getProgressByCourse(courseId: String): Flow<List<UserProgressEntity>>
    
    @Query("SELECT * FROM user_progress WHERE lessonId = :lessonId")
    fun getProgressByLesson(lessonId: String): Flow<UserProgressEntity?>
    
    @Query("""
        SELECT 
            COUNT(DISTINCT lessonId) as totalLessonsCompleted,
            COUNT(DISTINCT activityId) as totalActivitiesCompleted,
            SUM(timeSpentSeconds) / 60 as totalTimeSpentMinutes,
            AVG(CAST(score AS FLOAT) / maxScore * 100) as averageScore
        FROM user_progress 
        WHERE status = 'COMPLETED'
    """)
    fun getUserStatsRaw(): Flow<UserStatsRaw?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserProgressEntity)
    
    @Update
    suspend fun updateProgress(progress: UserProgressEntity)
    
    @Query("DELETE FROM user_progress WHERE courseId = :courseId")
    suspend fun deleteProgressByCourse(courseId: String)
}

/**
 * Clase auxiliar para estadísticas de usuario
 */
data class UserStatsRaw(
    val totalLessonsCompleted: Int,
    val totalActivitiesCompleted: Int,
    val totalTimeSpentMinutes: Long,
    val averageScore: Float?
)

/**
 * DAO para operaciones de Perfil de Usuario
 */
@Dao
interface UserProfileDao {
    
    @Query("SELECT * FROM user_profile LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfileEntity)
    
    @Update
    suspend fun updateUserProfile(profile: UserProfileEntity)
}

/**
 * DAO para operaciones de Vocabulario
 */
@Dao
interface VocabularyDao {
    
    @Query("SELECT * FROM vocabulary WHERE lessonId = :lessonId")
    fun getVocabularyByLesson(lessonId: String): Flow<List<VocabularyEntity>>
    
    @Query("SELECT * FROM vocabulary WHERE isFavorite = 1")
    fun getFavoriteVocabulary(): Flow<List<VocabularyEntity>>
    
    @Query("SELECT * FROM vocabulary WHERE word LIKE '%' || :query || '%' OR meaning LIKE '%' || :query || '%'")
    fun searchVocabulary(query: String): Flow<List<VocabularyEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabulary(vocabulary: VocabularyEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabularyList(vocabulary: List<VocabularyEntity>)
    
    @Query("UPDATE vocabulary SET isFavorite = :isFavorite WHERE id = :vocabularyId")
    suspend fun updateFavoriteStatus(vocabularyId: String, isFavorite: Boolean)
    
    @Query("UPDATE vocabulary SET timesReviewed = timesReviewed + 1 WHERE id = :vocabularyId")
    suspend fun incrementReviewCount(vocabularyId: String)
}
