package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.ReservarComidaScreen

class ReservarComidaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReservarComidaScreen(
                onNavigateBack = { onBackPressed() }  // Usa onBackPressed para retroceder
            )
        }
    }
}



