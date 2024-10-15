package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.CarritoScreenOnly

class CarritoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products = intent.getParcelableArrayListExtra<Product>("products") ?: listOf()
            val navController = rememberNavController()  // Crear el navController

            // Declarar el estado de los productos de manera correcta
            var updatedProducts by rememberSaveable { mutableStateOf(products) }

            // Obtener el ViewModel del carrito
            val carritoViewModel: CarritoViewModel = viewModel()

            // Llamada al Composable de la pantalla del carrito
            CarritoScreenOnly(
                navController = navController,  // <-- Pasar navController aquí
                products = updatedProducts,
                onBackPressed = { finish() },
                onRemoveProduct = { product ->
                    updatedProducts = updatedProducts.filter { it.id != product.id }  // Eliminar producto
                    carritoViewModel.eliminarProducto(product)  // Eliminar también del ViewModel
                },
                onSaveProduct = { /* Aún sin implementar */ },
                carritoViewModel = carritoViewModel  // Pasar el ViewModel al Composable
            )

        }
    }
}

