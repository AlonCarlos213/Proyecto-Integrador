package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel,
    selectedProducts: List<Product>,  // Recibir los productos seleccionados
    onPaymentSuccess: () -> Unit
) {
    var termsAccepted by remember { mutableStateOf(false) }
    val buttonColor = if (termsAccepted) Color(0xFF4CAF50) else Color.Gray
    val totalAmount = if (selectedProducts.isNotEmpty()) {
        selectedProducts.sumOf { it.price }
    } else {
        0.00
    }
    val pointsEarned = if (selectedProducts.isNotEmpty()) {
        selectedProducts.sumOf { it.points }
    } else {
        0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Realizar Pago") },
                navigationIcon = {
                    IconButton(onClick = {
                        // La flecha solo debe navegar hacia la pantalla de carrito, sin borrar el carrito
                        navController.navigate("carrito") {
                            popUpTo("carrito") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver al carrito")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Mostrar la lista de productos seleccionados
                LazyColumn {
                    items(selectedProducts) { product ->
                        Text(text = product.name)
                        Text(text = "S/ ${product.price}")
                        Text(text = "Puntos: ${product.points}")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Total a pagar: S/ $totalAmount",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Puntos a ganar: $pointsEarned", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(32.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it }
                    )
                    Text("Acepto los términos y condiciones")
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (termsAccepted && selectedProducts.isNotEmpty()) {
                            // Verificar si la lista de productos no está vacía antes de proceder
                            try {
                                onPaymentSuccess()
                                navController.navigate("purchase_summary/$totalAmount/$pointsEarned")
                            } catch (e: Exception) {
                                println("Error durante la navegación: ${e.message}")
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    enabled = termsAccepted
                ) {
                    Text("Realizar Compra")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón rojo para "Cancelar compra"
                Button(
                    onClick = {
                        // Aquí sí limpiamos el carrito al cancelar la compra
                        carritoViewModel.clearCart()
                        println("Carrito limpiado, navegando a home")  // Log para verificar
                        try {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }  // Navegar al menú principal
                            }
                        } catch (e: Exception) {
                            println("Error al cancelar la compra: ${e.message}")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)  // Botón rojo
                ) {
                    Text("Cancelar compra")
                }
            }
        }
    )
}











