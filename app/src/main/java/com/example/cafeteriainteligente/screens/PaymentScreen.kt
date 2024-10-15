package com.example.cafeteriainteligente.screens

import android.content.Intent
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
import com.example.cafeteriainteligente.PurchaseSummaryActivity
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel,
    selectedProducts: List<Product>,
    onPaymentSuccess: () -> Unit
) {
    var termsAccepted by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
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
                        showDialog = true // Mostrar el diálogo cuando se presiona la flecha
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
                            // Aquí generamos el Intent para pasar los productos, el total y los puntos ganados
                            val context = navController.context // Obtén el contexto
                            val intent = Intent(context, PurchaseSummaryActivity::class.java)
                            intent.putExtra("totalAmount", totalAmount) // Pasar el totalAmount
                            intent.putExtra("pointsEarned", pointsEarned) // Pasar los puntos ganados
                            intent.putParcelableArrayListExtra("purchasedProducts", ArrayList(selectedProducts)) // Pasar productos comprados como ArrayList
                            context.startActivity(intent) // Navegar a la pantalla de resumen de compra
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    enabled = termsAccepted
                ) {
                    Text("Realizar Compra")
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Cuadro de diálogo para confirmar la cancelación
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        title = { Text(text = "Cancelar compra") },
                        text = { Text(text = "¿Estás seguro que quieres cancelar tu compra?") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    carritoViewModel.clearCart()
                                    navController.navigate("carrito") {
                                        popUpTo("carrito") { inclusive = true }
                                    }
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                            ) {
                                Text("Aceptar", color = Color.White)
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text("Cancelar", color = Color.Black)
                            }
                        }
                    )
                }
            }
        }
    )
}

