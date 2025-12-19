package com.ars.paulet.domain.model

/**
 * Representa una lección individual dentro de un curso
 * Cada lección contiene 4 actividades: Audición, Conversación, Lectura, Escritura
 * Basado en Irodori - Japonés para la vida cotidiana
 */
data class Lesson(
    val id: String,
    val courseId: String,
    val number: Int,
    val title: String,
    val titleJapanese: String,
    val description: String,
    val activities: List<Activity> = emptyList(),
    val isCompleted: Boolean = false,
    val isUnlocked: Boolean = false,
    val progress: Float = 0f, // 0.0 to 1.0
    val imageUrl: String? = null
)

/**
 * Representa una actividad dentro de una lección
 */
data class Activity(
    val id: String,
    val lessonId: String,
    val type: ActivityType,
    val title: String,
    val titleJapanese: String,
    val content: ActivityContent,
    val isCompleted: Boolean = false,
    val order: Int
)

/**
 * Tipos de actividades disponibles en cada lección
 */
enum class ActivityType {
    AUDICION,       // 聴解 - Comprensión auditiva
    CONVERSACION,   // 会話 - Práctica de conversación
    LECTURA,        // 読解 - Comprensión de lectura
    ESCRITURA       // 作文 - Práctica de escritura
}

/**
 * Contenido específico de cada tipo de actividad
 */
sealed class ActivityContent {
    
    data class Listening(
        val audioUrl: String,
        val transcript: String? = null,
        val transcriptJapanese: String? = null,
        val questions: List<Question> = emptyList(),
        val duration: Long = 0L // en milisegundos
    ) : ActivityContent()
    
    data class Conversation(
        val dialogues: List<Dialogue> = emptyList(),
        val audioUrl: String? = null,
        val vocabulary: List<VocabularyItem> = emptyList()
    ) : ActivityContent()
    
    data class Reading(
        val text: String,
        val textJapanese: String,
        val furigana: String? = null,
        val questions: List<Question> = emptyList(),
        val vocabulary: List<VocabularyItem> = emptyList()
    ) : ActivityContent()
    
    data class Writing(
        val prompt: String,
        val promptJapanese: String,
        val examples: List<String> = emptyList(),
        val hints: List<String> = emptyList()
    ) : ActivityContent()
}

/**
 * Diálogo para actividades de conversación
 */
data class Dialogue(
    val speaker: String,
    val speakerJapanese: String,
    val text: String,
    val textJapanese: String,
    val audioUrl: String? = null
)

/**
 * Pregunta para evaluación
 */
data class Question(
    val id: String,
    val text: String,
    val textJapanese: String,
    val options: List<String> = emptyList(),
    val correctAnswer: Int = 0, // índice de la respuesta correcta
    val type: QuestionType = QuestionType.MULTIPLE_CHOICE
)

enum class QuestionType {
    MULTIPLE_CHOICE,
    TRUE_FALSE,
    FILL_IN_BLANK,
    ORDERING
}

/**
 * Elemento de vocabulario
 */
data class VocabularyItem(
    val word: String,
    val reading: String, // Hiragana/Katakana
    val meaning: String,
    val example: String? = null,
    val exampleMeaning: String? = null,
    val audioUrl: String? = null
)
