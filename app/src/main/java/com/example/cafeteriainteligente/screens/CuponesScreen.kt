package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cafeteriainteligente.R
import com.example.cafeteriainteligente.data.getCuponesList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuponesScreen(onNavigateToHome: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showCupones by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedCuponDetails by remember { mutableStateOf<String?>(null) }

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
                            try {
                                delay(1000)
                                showCupones = true
                                snackbarHostState.showSnackbar("Cupones cargados correctamente")
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar("Error al cargar los cupones")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text(text = "Mostrar Cupones", color = Color.White)
                }
            } else {
                LazyColumn {
                    items(getCuponesList()) { cupon ->
                        CuponItem(
                            title = cupon.title,
                            description = cupon.description,
                            imageResId = when (cupon.title) {
                                "S/ 5 OFF en Café Americano" -> R.drawable.menu2
                                "2x1 en Capuchino" -> R.drawable.menu3
                                "15% de descuento en Sandwiches" -> R.drawable.menu4
                                "S/ 3 OFF en Muffins" -> R.drawable.menu5
                                "10% de descuento en Menú del Día" -> R.drawable.menu1
                                else -> R.drawable.ic_coupon // Imagen por defecto si no hay coincidencia
                            },
                            onApplyClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Cupón aplicado correctamente")
                                }
                            },
                            onInfoClick = {
                                selectedCuponDetails = cupon.details
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

            selectedCuponDetails?.let { details ->
                CuponDetailsDialog(
                    details = details,
                    onDismiss = { selectedCuponDetails = null }
                )
            }
        }
    }
}




