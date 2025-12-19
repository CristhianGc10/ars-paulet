package com.ars.paulet

import android.app.Application
import com.ars.paulet.data.local.database.AppDatabase

/**
 * Clase Application de Ars Paulet
 * Inicialización de componentes globales
 */
class ArsPauletApp : Application() {
    
    // Base de datos (lazy initialization)
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Inicializaciones adicionales aquí
        initializeApp()
    }
    
    private fun initializeApp() {
        // TODO: Inicializar dependencias
        // - Coil para imágenes
        // - Analytics (si se usa)
        // - Crash reporting (si se usa)
    }
    
    companion object {
        lateinit var instance: ArsPauletApp
            private set
    }
}
