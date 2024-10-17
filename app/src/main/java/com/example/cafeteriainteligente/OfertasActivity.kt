package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.OfertasScreen

class OfertasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfertasScreen(onNavigateToHome = { finish() })
        }
    }
}
