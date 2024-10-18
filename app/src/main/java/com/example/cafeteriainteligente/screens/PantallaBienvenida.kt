package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cafeteriainteligente.R
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun PantallaBienvenida(navController: NavController) {
    val scale = remember { Animatable(0f) }
    var progress by remember { mutableStateOf(0f) }

    // Animación al iniciar
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000
            )
        )

        // Simular progreso de carga
        for (i in 0..100) {
            progress = i / 100f
            delay(30) // Tiempo entre cada incremento de progreso
        }

        // Navegar a la pantalla principal cuando termine la animación
        navController.navigate("home")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1ACD81)), // Fondo verde
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen del logo con animación
            Image(
                painter = painterResource(id = R.drawable.logo_enzi),
                contentDescription = "Logo Enzi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale.value)
                    .background(Color.White, CircleShape)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Barra de progreso para simular la carga
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(8.dp)
                    .background(Color(0xFF0FA968)) // Color de fondo de la barra
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    color = Color.White,
                    modifier = Modifier.fillMaxSize() // La barra de progreso rellena el fondo
                )
            }
        }
    }
}
