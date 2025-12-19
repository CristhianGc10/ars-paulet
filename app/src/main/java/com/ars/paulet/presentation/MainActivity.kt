package com.ars.paulet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ars.paulet.presentation.navigation.ArsPauletNavigation
import com.ars.paulet.presentation.theme.ArsPauletTheme

/**
 * Actividad principal de Ars Paulet
 * Punto de entrada de la aplicación
 */
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Habilitar edge-to-edge display
        enableEdgeToEdge()
        
        setContent {
            ArsPauletTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArsPauletNavigation()
                }
            }
        }
    }
}
