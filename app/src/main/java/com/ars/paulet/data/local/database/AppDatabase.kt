package com.ars.paulet.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ars.paulet.data.local.dao.*
import com.ars.paulet.data.local.entity.*

/**
 * Base de datos principal de la aplicación
 * Contiene todas las tablas para cursos, lecciones, actividades, progreso y vocabulario
 */
@Database(
    entities = [
        CourseEntity::class,
        LessonEntity::class,
        ActivityEntity::class,
        UserProgressEntity::class,
        UserProfileEntity::class,
        VocabularyEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun courseDao(): CourseDao
    abstract fun lessonDao(): LessonDao
    abstract fun activityDao(): ActivityDao
    abstract fun progressDao(): ProgressDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun vocabularyDao(): VocabularyDao
    
    companion object {
        private const val DATABASE_NAME = "ars_paulet_database"
        
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration() // Para desarrollo, cambiar en producción
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
