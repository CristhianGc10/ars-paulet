package com.ars.paulet.domain.model

/**
 * Representa un curso principal (Inicial, Básico 1, Básico 2)
 * Filosofía Wabi-Sabi: Simplicidad y esencia
 */
data class Course(
    val id: String,
    val name: String,
    val nameJapanese: String,
    val description: String,
    val level: CourseLevel,
    val totalLessons: Int = 18,
    val completedLessons: Int = 0,
    val imageUrl: String? = null,
    val isUnlocked: Boolean = false
)

enum class CourseLevel {
    INICIAL,      // Nivel Inicial - 18 lecciones
    BASICO_1,     // Básico 1 - 18 lecciones
    BASICO_2      // Básico 2 - 18 lecciones
}
