package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseSummaryScreen(
    navController: NavController,
    totalAmount: Double,  // Recibir el total pagado
    pointsEarned: Int     // Recibir los puntos ganados
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumen de la Compra") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar y regresar a inicio")
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
                Text("¡Compra realizada con éxito!", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Total pagado: S/ $totalAmount", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Puntos ganados: $pointsEarned", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = {
                    // Vuelve a la pantalla principal
                    navController.navigate("home")
                }) {
                    Text("Cerrar")
                }
            }
        }
    )
}


