package com.example.cafeteriainteligente

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.ReservarComidaScreen

class ReservarComidaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Pasa la función de navegación al composable
            ReservarComidaScreen(
                onNavigateToHome = { navigateToHome() }
            )
        }
    }

    private fun navigateToHome() {
        // Aquí puedes manejar la navegación de vuelta a la pantalla de inicio
        val intent = Intent(this, MainActivity::class.java) // Suponiendo que MainActivity sea tu Home
        startActivity(intent)
        finish()  // Finaliza la actividad actual
    }
}

