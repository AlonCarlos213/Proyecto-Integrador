package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ReservarComidaScreen(
    onNavigateToHome: () -> Unit  // Agrega la navegación para ir a la pantalla de inicio
) {
    var selectedCategory by remember { mutableStateOf("Café") }
    var suggestedProduct by remember { mutableStateOf(TextFieldValue("")) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Título
        Text(
            text = "Sugerencia de Comida",
            style = MaterialTheme.typography.h5.copy(fontSize = 24.sp),
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Pregunta 1: Selección de categoría
        Text(text = "¿Qué categoría de la cafetería te gustaría sugerir?")
        DropdownMenuExample(
            options = listOf("Café", "Bebidas", "Postres", "Dulces", "Snack", "Sándwich", "Empanada", "Comida", "Otros"),
            selectedOption = selectedCategory,
            onOptionSelected = { selectedCategory = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pregunta 2: Campo de texto para sugerir un platillo
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

        // Botón de Sugerir
        Button(
            onClick = {
                // Mostrar un Snackbar para confirmar el envío de la sugerencia
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Tu sugerencia se ha enviado correctamente"
                    )
                }
                // Navega de vuelta a la pantalla de inicio después de un pequeño retraso
                scope.launch {
                    kotlinx.coroutines.delay(1000)  // Simula un pequeño retraso antes de la navegación
                    onNavigateToHome()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))  // Cambia el color a verde
        ) {
            Text(text = "Sugerir", color = Color.White)
        }

        // Host del Snackbar
        SnackbarHost(hostState = snackbarHostState)
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
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
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

