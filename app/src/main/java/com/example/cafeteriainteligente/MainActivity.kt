package com.example.cafeteriainteligente

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.screens.MainScreen
import com.example.cafeteriainteligente.screens.PantallaBienvenida
import com.example.cafeteriainteligente.screens.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.example.cafeteriainteligente.ui.theme.CafeteriaInteligenteTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            CafeteriaInteligenteTheme {
                val navController = rememberNavController()
                val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
                val hasSeenWelcomeScreen = sharedPreferences.getBoolean("hasSeenWelcomeScreen", false)

                // Verificar si el usuario ya está autenticado
                val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
                // Definir el NavHost con la pantalla correcta según el estado
                NavHost(
                    navController = navController,
                    startDestination = if (hasSeenWelcomeScreen) {
                        if (isLoggedIn) "home" else "login"
                    } else {
                        "bienvenida"
                    }
                ) {
                    // Pantalla de bienvenida
                    composable("bienvenida") {
                        PantallaBienvenida(navController = navController)
                        sharedPreferences.edit().putBoolean("hasSeenWelcomeScreen", true).apply()
                    }

                    // Pantalla de inicio de sesión con Google
                    composable("login") {
                        LoginScreen(navController = navController)
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


