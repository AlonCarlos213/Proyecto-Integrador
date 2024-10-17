package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cafeteriainteligente.R

@Composable
fun CuponItem(
    title: String,
    description: String,
    imageResId: Int?,
    onApplyClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    var isApplied by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Mostrar la imagen del cupón
            if (imageResId != null) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Imagen del cupón",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 8.dp)
                )
            } else {
                // Si no hay recurso de imagen, muestra un recurso local predeterminado
                Image(
                    painterResource(id = R.drawable.ic_coupon),
                    contentDescription = "Cupón predeterminado",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 8.dp)
                )
            }

            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    onClick = {
                        isApplied = !isApplied
                        onApplyClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isApplied) Color.Gray else Color(0xFF4CAF50)
                    )
                ) {
                    Text(text = if (isApplied) "Cancelar" else "Aplicar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onInfoClick) {
                    Icon(Icons.Filled.Info, contentDescription = "Info")
                }
                // Icono de check si el cupón ha sido aplicado
                if (isApplied) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = "Aplicado", tint = Color.Green)
                }
            }
        }
    }
}



