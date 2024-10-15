package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.screens.PurchaseSummaryScreen
import androidx.navigation.compose.rememberNavController

class PurchaseSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener los valores pasados por la Intent
        val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)
        val pointsEarned = intent.getIntExtra("pointsEarned", 0)

        setContent {
            // Crear navController por si lo necesitas para futuras navegaciones
            val navController = rememberNavController()

            // Obtener el ViewModel del carrito
            val carritoViewModel: CarritoViewModel = viewModel()

            // Llamada al Composable de la pantalla del resumen de compra
            PurchaseSummaryScreen(
                navController = navController,  // Pasar el NavController
                totalAmount = totalAmount,      // Pasar el totalAmount
                pointsEarned = pointsEarned     // Pasar los pointsEarned
            )
        }
    }
}
