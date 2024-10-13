package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.CarritoScreenOnly

class CarritoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products = intent.getParcelableArrayListExtra<Product>("products") ?: listOf()

            // Declarar el estado de los productos de manera correcta
            var updatedProducts by rememberSaveable { mutableStateOf(products) }

            // Llamada al Composable de la pantalla del carrito
            CarritoScreenOnly(
                products = updatedProducts,
                onBackPressed = { finish() },
                onRemoveProduct = { product ->
                    updatedProducts = updatedProducts.filter { it.id != product.id }  // Eliminar producto
                },
                onSaveProduct = { /* Aún sin implementar */ }
            )
        }
    }
}


