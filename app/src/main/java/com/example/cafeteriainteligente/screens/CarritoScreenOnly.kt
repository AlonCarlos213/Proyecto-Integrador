package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.models.CarritoViewModel
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.cafeteriainteligente.PaymentActivity
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreenOnly(
    products: List<Product>,
    onBackPressed: () -> Unit,
    onRemoveProduct: (Product) -> Unit,
    onSaveProduct: (Product) -> Unit,
    carritoViewModel: CarritoViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val gson = Gson()  // Inicializamos Gson
    val quantities = remember { mutableStateMapOf<Product, Int>().apply {
        products.forEach { product -> this[product] = 1 }
    }}
    var updatedProducts by remember { mutableStateOf(products) }
    var showExitDialog by remember { mutableStateOf(false) } // Control para mostrar el diálogo
    var inCheckoutProcess by remember { mutableStateOf(false) } // Controla si está en proceso de pago

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Solo mostrar el diálogo si ya está en proceso de compra (presionó "Comprar ahora")
                        if (inCheckoutProcess) {
                            showExitDialog = true
                        } else {
                            onBackPressed() // No mostrar advertencia, simplemente navegar
                        }
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Tu carrito",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )

                if (updatedProducts.isEmpty()) {
                    Text(
                        text = "Tu carrito está vacío.",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(updatedProducts) { product ->
                            val quantity = quantities[product] ?: 1
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = product.imageRes),
                                    contentDescription = product.name,
                                    modifier = Modifier.size(60.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 16.dp)
                                ) {
                                    Text(
                                        text = product.name,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        text = "S/ ${product.price}",
                                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                                    )
                                    Row {
                                        TextButton(onClick = {
                                            onRemoveProduct(product)
                                            carritoViewModel.eliminarProducto(product)
                                            updatedProducts = updatedProducts.filter { it.id != product.id }
                                        }) {
                                            Text("Eliminar", color = Color.Red)
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        TextButton(onClick = { onSaveProduct(product) }) {
                                            Text("Guardar")
                                        }
                                    }
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(
                                        onClick = {
                                            if (quantity > 1) {
                                                quantities[product] = quantity - 1
                                            }
                                        }
                                    ) {
                                        Icon(imageVector = Icons.Default.Remove, contentDescription = "Disminuir cantidad")
                                    }
                                    Text(
                                        text = quantity.toString(),
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                    )
                                    IconButton(
                                        onClick = {
                                            if (quantity < 5) {
                                                quantities[product] = quantity + 1
                                            }
                                        }
                                    ) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = "Aumentar cantidad")
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val productsJson = gson.toJson(updatedProducts)
                            val intent = Intent(context, PaymentActivity::class.java)
                            intent.putExtra("selectedProducts", productsJson)
                            context.startActivity(intent)
                            inCheckoutProcess = true // Marcar que ha comenzado el proceso de compra
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Comprar ahora")
                    }
                }
            }
        }
    )

    // Diálogo de advertencia solo si está en proceso de compra
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },  // Cierra el diálogo si se hace clic fuera
            title = { Text(text = "Cancelar compra") },
            text = { Text("¿No quieres seguir con tu compra? Si sales, se restablecerán los productos seleccionados.") },
            confirmButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                        carritoViewModel.clearCart()
                        navController.navigate("home") { popUpTo("home") { inclusive = true } }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showExitDialog = false },  // Mantiene al usuario en la pantalla
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

















