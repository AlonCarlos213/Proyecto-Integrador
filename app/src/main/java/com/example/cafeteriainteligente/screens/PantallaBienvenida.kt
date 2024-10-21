package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PantallaBienvenida(navController: NavController) {
    val auth = FirebaseAuth.getInstance() // Agregar FirebaseAuth
    val user = auth.currentUser // Verificar si el usuario está autenticado
    val scale = remember { Animatable(0f) }
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1000))

        for (i in 0..100) {
            progress = i / 100f
            delay(30)
        }

        // Redirigir dependiendo de si el usuario está autenticado
        if (user != null) {
            navController.navigate("home") // Navegar a Home si está autenticado
        } else {
            navController.navigate("login") // Navegar a Login si no está autenticado
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF1ACD81)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo_enzi),
                contentDescription = "Logo Enzi",
                modifier = Modifier.size(200.dp).scale(scale.value)
            )
            Spacer(modifier = Modifier.height(32.dp))
            LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth(0.5f))
        }
    }
}
