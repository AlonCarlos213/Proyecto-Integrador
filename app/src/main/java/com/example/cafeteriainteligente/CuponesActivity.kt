package com.example.cafeteriainteligente

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cafeteriainteligente.screens.CuponesScreen

class CuponesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuponesScreen(
                onNavigateToHome = { navigateToHome() }
            )
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)  // Asegúrate que esto dirija al inicio
        startActivity(intent)
        finish()
    }
}
