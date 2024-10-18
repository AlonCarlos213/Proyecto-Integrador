package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ReservarComidaScreen(onNavigateBack: () -> Unit) {  // Cambia a onNavigateBack
    var selectedCategory by remember { mutableStateOf("Café") }
    var suggestedProduct by remember { mutableStateOf(TextFieldValue("")) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sugerencia de Comida") },
                backgroundColor = Color(0xFF4CAF50),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {  // Llama a onNavigateBack en lugar de navegar a Home
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "¿Qué categoría de la cafetería te gustaría sugerir?")
            DropdownMenuExample(
                options = listOf("Café", "Bebidas", "Postres", "Dulces", "Snack", "Sándwich", "Empanada", "Comida", "Otros"),
                selectedOption = selectedCategory,
                onOptionSelected = { selectedCategory = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "¿Qué producto o platillo sugieres para los demás usuarios?")
            BasicTextField(
                value = suggestedProduct,
                onValueChange = { suggestedProduct = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(100.dp)
                    .background(MaterialTheme.colors.surface),
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Tu sugerencia se ha enviado correctamente"
                        )
                    }
                    scope.launch {
                        kotlinx.coroutines.delay(1000)
                        onNavigateBack()  // Llama onNavigateBack después de enviar la sugerencia
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text(text = "Sugerir", color = Color.White)
            }
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuExample(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Selecciona una categoría") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(selectionOption)
                    expanded = false
                }) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}


