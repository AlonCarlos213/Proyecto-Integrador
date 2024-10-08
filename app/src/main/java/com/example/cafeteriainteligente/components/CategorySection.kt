package com.example.cafeteriainteligente.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cafeteriainteligente.R

// Definir datos de categorías con iconos y nombres
data class Category(val iconRes: Int, val title: String)

@Composable
fun CategoriesSection(modifier: Modifier = Modifier) {
    val categories = listOf(
        Category(R.drawable.cafe, "Café"),
        Category(R.drawable.bebidas, "Bebidas"),
        Category(R.drawable.postres, "Postres"),
        Category(R.drawable.dulces, "Dulces"),
        Category(R.drawable.snack, "Snack"),
        Category(R.drawable.sandwich, "Sandwich"),
        Category(R.drawable.empanadas, "Empanada"),
        Category(R.drawable.comida, "Comida"),
        Category(R.drawable.otros, "Otros")
    )

    // Modificar el contenedor del carrusel con scroll horizontal y espaciado
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 5.dp)
            .horizontalScroll(rememberScrollState()),  // Permitir desplazamiento horizontal
        horizontalArrangement = Arrangement.spacedBy(16.dp)  // Espaciado entre categorías
    ) {
        // Generar cada categoría en una tarjeta pequeña
        categories.forEach { category ->
            CategoryCard(category = category)
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Column(
        modifier = Modifier
            .width(60.dp)  // Ajustar el ancho de cada tarjeta para alineación uniforme
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = category.iconRes),
            contentDescription = category.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)  // Tamaño del icono de la categoría
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.height(4.dp))  // Espacio entre el icono y el texto
        Text(
            text = category.title,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            color = Color.Black
        )
    }
}
