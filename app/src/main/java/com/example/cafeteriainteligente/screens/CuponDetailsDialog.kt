package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CuponDetailsDialog(details: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Detalles del cupón") },
        text = {
            Text(text = details)
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cerrar")
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}
