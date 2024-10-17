package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IngresarCodigoCuponDialog(
    onDismiss: () -> Unit,
    onCuponAplicado: (String) -> Unit
) {
    var cuponCode by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Ingresar código de cupón", style = TextStyle(fontSize = 20.sp)) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = cuponCode,
                    onValueChange = { cuponCode = it },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 18.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    decorationBox = { innerTextField ->
                        if (cuponCode.isEmpty()) {
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
                    if (cuponCode.isNotEmpty()) {
                        onCuponAplicado(cuponCode)
                        onDismiss()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (cuponCode.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray
                ),
                enabled = cuponCode.isNotEmpty()
            ) {
                Text(text = "Agregar cupón", color = Color.White)
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}

