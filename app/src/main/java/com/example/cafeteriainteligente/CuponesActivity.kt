package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.CuponesScreen

class CuponesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuponesScreen(
                onNavigateBack = { onBackPressed() }  // Usa onBackPressed para retroceder sin reiniciar la actividad
            )
        }
    }
}

