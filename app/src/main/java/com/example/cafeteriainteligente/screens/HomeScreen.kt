package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cafeteriainteligente.R
import com.example.cafeteriainteligente.components.ImageCarousel
import com.example.cafeteriainteligente.components.CategoriesSection
import com.example.cafeteriainteligente.components.RecentViewedSection
import com.example.cafeteriainteligente.models.Product
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    cartProducts: List<Product>,  // Asegúrate de agregar este parámetro
    onAddToCart: (Product) -> Unit
) {
    val images = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
            cartProducts = cartProducts,  // Pasar la lista de productos en el carrito
            onAddToCart = { product ->
                onAddToCart(product)  // Llamar a la función de agregar al carrito correctamente
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
