package com.example.cafeteriainteligente

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.cafeteriainteligente.screens.MiCuentaScreen

class MiCuentaActivity : ComponentActivity() {  // Cambia a ComponentActivity
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MiCuentaScreen(onNavigateToHome = { finish() }) // Pantalla de la cuenta del usuario
            }
        }
    }


