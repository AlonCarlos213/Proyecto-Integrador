package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.AjustesScreen
import com.example.cafeteriainteligente.ui.theme.CafeteriaInteligenteTheme

class AjustesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeteriaInteligenteTheme {
                AjustesScreen(onNavigateToHome = {
                    finish() // Termina la actividad y regresa a la anterior (MainActivity)
                })
            }
        }
    }
}
