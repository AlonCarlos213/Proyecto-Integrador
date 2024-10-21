package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.AjustesScreen
import com.example.cafeteriainteligente.ui.theme.CafeteriaInteligenteTheme
import androidx.navigation.compose.rememberNavController

class AjustesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()  // Instancia de NavController

            CafeteriaInteligenteTheme {
                AjustesScreen(navController = navController, onNavigateToHome = {
                    finish() // Termina la actividad y regresa a la anterior
                })
            }
        }
    }
}
