package com.example.cafeteriainteligente

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.ui.theme.CafeteriaInteligenteTheme
import com.example.cafeteriainteligente.screens.MainScreen
import com.example.cafeteriainteligente.screens.PantallaBienvenida

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeteriaInteligenteTheme {
                val navController = rememberNavController()

                // Verifica si la pantalla de bienvenida ya fue mostrada usando SharedPreferences
                val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
                val hasSeenWelcomeScreen = sharedPreferences.getBoolean("hasSeenWelcomeScreen", false)

                // Definir el NavHost
                NavHost(
                    navController = navController,
                    startDestination = if (hasSeenWelcomeScreen) "home" else "bienvenida" // Si ya la vio, navegar al home
                ) {
                    // Pantalla de bienvenida
                    composable("bienvenida") {
                        PantallaBienvenida(navController = navController)
                        // Guardar que la pantalla de bienvenida ya fue vista
                        sharedPreferences.edit().putBoolean("hasSeenWelcomeScreen", true).apply()
                    }

                    // Pantalla principal
                    composable("home") {
                        MainScreen(navController = navController)
                    }
                }
            }
        }
    }
}

