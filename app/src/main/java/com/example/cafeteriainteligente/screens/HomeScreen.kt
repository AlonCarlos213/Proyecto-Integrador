package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cafeteriainteligente.R
import com.example.cafeteriainteligente.components.ImageCarousel
import com.example.cafeteriainteligente.components.CategoriesSection
import com.example.cafeteriainteligente.components.RecentViewedSection
import com.example.cafeteriainteligente.models.Product
import androidx.navigation.NavController
import com.example.cafeteriainteligente.models.CarritoViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    cartProducts: List<Product>,
    onAddToCart: (Product) -> Unit,
    onRemoveFromCart: (Product) -> Unit, // Agregar función para remover del carrito
    carritoViewModel: CarritoViewModel
) {
    val images = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5
    )

    // Usar SnackbarHostState en lugar de ScaffoldState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    // Estructura del Scaffold para mostrar mensajes flotantes
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } // Asignar el snackbarHostState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ImageCarousel(
                images = images,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategoriesSection()

            Spacer(modifier = Modifier.height(16.dp))

            RecentViewedSection(
                cartProducts = cartProducts,
                onAddToCart = { product ->
                    val alreadyInCart = cartProducts.any { it.id == product.id }
                    if (alreadyInCart) {
                        // Mostrar el mensaje flotante cuando el producto ya está en el carrito
                        scope.launch {
                            snackbarHostState.showSnackbar("Ya se agregó este producto a tu carrito")
                        }
                    } else {
                        onAddToCart(product)
                    }
                },
                onRemoveFromCart = { product -> // Definir el comportamiento de eliminación
                    onRemoveFromCart(product)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

