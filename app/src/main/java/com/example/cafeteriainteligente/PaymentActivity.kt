package com.example.cafeteriainteligente

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.PaymentScreen
import com.google.gson.Gson

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recuperar los productos seleccionados del Intent
        val productsJson = intent.getStringExtra("selectedProducts") ?: "[]"
        val selectedProducts = Gson().fromJson(productsJson, Array<Product>::class.java).toList()

        setContent {
            // Crear navController por si lo necesitas para futuras navegaciones
            val navController = rememberNavController()

            // Obtener el ViewModel del carrito
            val carritoViewModel: CarritoViewModel = viewModel()

            // Llamada al Composable de la pantalla de pago
            PaymentScreen(
                navController = navController,
                carritoViewModel = carritoViewModel,
                selectedProducts = selectedProducts,  // Pasar los productos seleccionados
                onPaymentSuccess = {
                    // Navegar al resumen de compra o terminar la actividad
                    val intent = Intent(this, PurchaseSummaryActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}
