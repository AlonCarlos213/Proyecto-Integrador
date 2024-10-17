package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.graphics.Shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngresarCodigoCuponDialog(
    onDismiss: () -> Unit,
    onCuponAplicado: (String) -> Unit
) {
    var cuponCode by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Ingresar código de cupón",
                style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = cuponCode,
                    onValueChange = { cuponCode = it },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(
                            color = Color(0xFFF1F1F1),
                            shape = MaterialTheme.shapes.medium // Bordes más redondeados
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    decorationBox = { innerTextField ->
                        if (cuponCode.text.isEmpty()) {
                            Text(
                                text = "Ingrese código",
                                style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                            )
                        }
                        innerTextField()
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (cuponCode.text.isNotEmpty()) {
                        onCuponAplicado(cuponCode.text)
                        onDismiss()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (cuponCode.text.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray
                ),
                enabled = cuponCode.text.isNotEmpty()
            ) {
                Text(text = "Agregar cupón", color = Color.White)
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}


