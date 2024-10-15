package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.screens.PurchaseSummaryScreen
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.models.Product

class PurchaseSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener los valores pasados por la Intent
        val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)
        val pointsEarned = intent.getIntExtra("pointsEarned", 0)
        val purchasedProducts = intent.getParcelableArrayListExtra<Product>("purchasedProducts") ?: emptyList()

        setContent {
            val navController = rememberNavController()
            val carritoViewModel: CarritoViewModel = viewModel() // Obtener el ViewModel correctamente

            // Llamar a la pantalla de resumen de compra pasando el ViewModel
            PurchaseSummaryScreen(
                navController = navController,
                totalAmount = totalAmount,
                pointsEarned = pointsEarned,
                purchasedProducts = purchasedProducts,
                carritoViewModel = carritoViewModel  // Pasar el carritoViewModel aquí
            )
        }
    }
}

