package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cafeteriainteligente.R
import com.example.cafeteriainteligente.data.getOfertasList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfertasScreen(onNavigateToHome: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var selectedOfertaDetails by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ofertas") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToHome() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFC107),
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
            LazyColumn {
                items(getOfertasList()) { oferta ->
                    OfertaItem(
                        title = oferta.title,
                        description = oferta.description,
                        imageResId = oferta.imageResId,
                        onApplyClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Oferta aplicada correctamente")
                            }
                        },
                        onInfoClick = {
                            selectedOfertaDetails = oferta.details
                        }
                    )
                }
            }
        }

        selectedOfertaDetails?.let { details ->
            OfertaDetailsDialog(
                details = details,
                onDismiss = { selectedOfertaDetails = null }
            )
        }
    }
}


