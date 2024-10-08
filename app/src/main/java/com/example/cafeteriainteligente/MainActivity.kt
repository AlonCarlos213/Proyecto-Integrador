package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.ui.theme.CafeteriaInteligenteTheme
import com.example.cafeteriainteligente.screens.MainScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()  // Crea el NavController aquí
            CafeteriaInteligenteTheme {
                MainScreen(navController = navController)  // Pasa el controlador a MainScreen
            }
        }
    }
}
