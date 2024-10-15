package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cafeteriainteligente.models.Product
import kotlin.math.roundToInt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import com.example.cafeteriainteligente.models.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseSummaryScreen(
    navController: NavController,
    totalAmount: Double,
    pointsEarned: Int,
    purchasedProducts: List<Product>,
    carritoViewModel: CarritoViewModel  // Agregar el ViewModel del carrito para poder limpiar el carrito
) {
    var rating by remember { mutableStateOf(0) } // Guardar la calificación
    var feedbackText by remember { mutableStateOf("") } // Texto del comentario
    val ratingDescription = when (rating) {
        1 -> "Muy Malo"
        2 -> "Malo"
        3 -> "Bueno"
        4 -> "Muy Bueno"
        5 -> "Excelente"
        else -> ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumen de la Compra") },
                navigationIcon = {
                    IconButton(onClick = {
                        carritoViewModel.clearCart()
                        // Navegar a la pantalla principal (HomeScreen)
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar y regresar a HomeScreen")
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
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("¡Compra realizada con éxito!", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))

                // Mostrar los productos comprados
                Text("Productos comprados:", style = MaterialTheme.typography.bodyLarge)
                purchasedProducts.forEach { product ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${product.name} (x${product.quantity})")
                        Text("S/ ${(product.price * product.quantity).roundToInt()}") // Precio total por producto
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text("Total pagado: S/ $totalAmount", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Puntos ganados: $pointsEarned", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(32.dp))

                // Sección de calificación de 5 estrellas
                Text("Califica tu experiencia:")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        IconButton(onClick = { rating = i }) {
                            Icon(
                                imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                                contentDescription = "Estrella $i",
                                tint = if (i <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                Text(ratingDescription, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                // Comentario opcional
                Text("Deja un comentario (opcional):")
                BasicTextField(
                    value = feedbackText,
                    onValueChange = { feedbackText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(100.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                )
            }
        }
    )
}



