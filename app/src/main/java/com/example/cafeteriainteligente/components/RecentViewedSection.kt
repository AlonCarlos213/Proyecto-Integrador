package com.example.cafeteriainteligente.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cafeteriainteligente.R
import com.example.cafeteriainteligente.models.Product

@Composable
fun RecentViewedSection(
    cartProducts: List<Product>,  // Agregar parámetro de productos en el carrito
    onAddToCart: (Product) -> Unit
) {
    val products = listOf(
        Product(id = "1", imageRes = R.drawable.cafe, name = "Café Expreso", price = "5.00"),
        Product(id = "2", imageRes = R.drawable.bebidas, name = "Jugo de Naranja", price = "3.50"),
        Product(id = "3", imageRes = R.drawable.postres, name = "Torta de Fresa", price = "8.00"),
        Product(id = "4", imageRes = R.drawable.sandwich, name = "Sandwich de Pollo", price = "6.50")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Visto recientemente",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Ajuste para centrar las filas de productos en el ancho disponible
        products.chunked(2).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (rowItems.size < 2) 32.dp else 0.dp), // Centralizar si hay menos de 2 productos
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowItems.forEach { product ->
                    Box(modifier = Modifier.weight(1f)) {
                        val alreadyInCart = cartProducts.any { it.id == product.id }  // Verificar si el producto ya está en el carrito
                        ProductCard(
                            product,
                            onAddToCart,
                            alreadyInCart  // Pasar estado si ya está en el carrito
                        )
                    }
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f)) // Relleno para centrar
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun ProductCard(product: Product, onAddToCart: (Product) -> Unit, alreadyInCart: Boolean) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .size(width = 160.dp, height = 200.dp) // Ajustar tamaño del card
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), // Ajustar padding dentro del card
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "S/ ${product.price}",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            // Ajuste de botón para aumentar el tamaño
            Button(
                onClick = {
                    if (alreadyInCart) {
                        println("Ya se agregó este producto a tu carrito")
                    } else {
                        onAddToCart(product)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (alreadyInCart) Color.Gray else Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp) // Tamaño del botón ajustado
            ) {
                Text(if (alreadyInCart) "Ya en carrito" else "Pedir ya", fontSize = 14.sp, color = Color.White) // Ajuste de texto del botón
            }
        }
    }
}






