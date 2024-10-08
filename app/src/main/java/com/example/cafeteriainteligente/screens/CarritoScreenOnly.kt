package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.cafeteriainteligente.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreenOnly(
    products: List<Product>,  // Recibir lista de productos acumulados desde CarritoActivity
    onBackPressed: () -> Unit  // Definir el parámetro `onBackPressed`
) {
    // Crear un estado para manejar la cantidad de cada producto
    val quantities = remember { mutableStateMapOf<Product, Int>().apply {
        products.forEach { product -> this[product] = 1 }  // Inicializar cada producto con cantidad 1
    } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Regresar")
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
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                // Verificar si la lista de productos está vacía
                if (products.isEmpty()) {
                    Text(
                        text = "Tu carrito está vacío.",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                        modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(products) { product ->  // Mostrar productos con cantidad ajustable
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
                                }

                                // Sección de botones de incremento y decremento
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
                                            if (quantity < 5) {  // Limitar el número máximo de unidades a 5
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
                        onClick = { /* Acción de compra */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Comprar ahora")
                    }
                }
            }
        }
    )
}




