package com.example.cafeteriainteligente.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cafeteriainteligente.ui.theme.Jade500
import com.example.cafeteriainteligente.ui.theme.Jade600
import com.example.cafeteriainteligente.ui.theme.Typography
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesScreen(navController: NavController, onNavigateToHome: () -> Unit) {
    val auth =FirebaseAuth.getInstance()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración", style = Typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToHome() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Jade500,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Tu cuenta está protegida",
                style = Typography.bodyLarge,
                color = Jade600
            )
            Text(
                text = "Cafetería Inteligente protege tu información personal y la mantiene privada y segura.",
                style = Typography.bodySmall,
                color = Color.Gray
            )

            // Sección de ajustes
            AjusteItem(title = "Seguridad de la cuenta", icon = Icons.Filled.Lock)
            AjusteItem(title = "Privacidad", icon = Icons.Filled.PrivacyTip)
            AjusteItem(title = "Permisos", icon = Icons.Filled.Settings)
            AjusteItem(title = "Centro de seguridad", icon = Icons.Filled.Security)

            Spacer(modifier = Modifier.height(16.dp))

            // Opciones adicionales
            AjusteItem(title = "Métodos de pago", icon = Icons.Filled.Payment)
            AjusteItem(title = "Idioma", icon = Icons.Filled.Language)
            AjusteItem(title = "Moneda", icon = Icons.Filled.Money)

            Spacer(modifier = Modifier.height(16.dp))

            // Opciones legales y de cuenta
            AjusteItem(title = "Términos y políticas legales", icon = Icons.Filled.Description)
            AjusteItem(title = "Cambiar cuenta", icon = Icons.Filled.AccountBox) {
                auth.signOut() // Cerrar la sesión actual
                try {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }  // Limpia la pila de navegación
                    }
                } catch (e: Exception) {
                    e.printStackTrace()  // Manejo de la excepción si ocurre un error
                }
            }

            AjusteItem(title = "Cerrar sesión", icon = Icons.Filled.Logout) {
                auth.signOut() // Cerrar la sesión actual
                try {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }  // Limpia la pila de navegación
                    }
                } catch (e: Exception) {
                    e.printStackTrace()  // Manejo de la excepción si ocurre un error
                }
            }
        }
    }
}

@Composable
fun AjusteItem(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acción al hacer clic */ }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Jade600,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = Typography.bodyLarge,
            color = Color.Black
        )
    }
}
@Composable
fun AjusteItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }  // Acción cuando se hace clic
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Jade600,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = Typography.bodyLarge,
            color = Color.Black
        )
    }
}
