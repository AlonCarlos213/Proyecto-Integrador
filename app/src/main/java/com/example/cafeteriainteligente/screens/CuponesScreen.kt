package com.example.cafeteriainteligente.screens

import com.example.cafeteriainteligente.screens.IngresarCodigoCuponDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuponesScreen(onNavigateToHome: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showCupones by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cupones") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToHome() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Aquí puedes ver todos tus cupones disponibles.")
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(text = "Ingresar código de cupón", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!showCupones) {
                Button(
                    onClick = {
                        scope.launch {
                            delay(1000) // Simula un pequeño delay
                            showCupones = true
                            snackbarHostState.showSnackbar("Cupones cargados correctamente")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text(text = "Mostrar Cupones", color = Color.White)
                }
            } else {
                LazyColumn {
                    items(5) { index ->
                        CuponItem(
                            title = "S/ ${index * 100} OFF en Producto ${index + 1}",
                            description = "Compra mínima S/ ${index * 1000}",
                            onApplyClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Cupón aplicado correctamente")
                                }
                            }
                        )
                    }
                }
            }

            if (showDialog) {
                IngresarCodigoCuponDialog(
                    onDismiss = { showDialog = false },
                    onCuponAplicado = { codigo ->
                        scope.launch {
                            snackbarHostState.showSnackbar("Cupón $codigo aplicado correctamente")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CuponItem(title: String, description: String, onApplyClick: () -> Unit) {
    var isApplied by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    isApplied = !isApplied
                    onApplyClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isApplied) Color.Gray else Color(0xFF4CAF50)
                )
            ) {
                Text(text = if (isApplied) "Buscar Productos" else "Aplicar", color = Color.White)
            }
        }
    }
}

