package com.example.cafeteriainteligente.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cafeteriainteligente.R

@Composable
fun NavigationDrawer(
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        // Agrega un `Spacer` para empujar todo hacia abajo
        Spacer(modifier = Modifier.height(40.dp)) // Espacio en la parte superior

        // Sección superior en verde con el perfil de usuario
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4CAF50)) // Fondo verde
                .padding(16.dp)
        ) {
            // Perfil del usuario
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Icono del perfil
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(Color.LightGray, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Nombre del usuario y enlace de perfil
                Column {
                    Text(
                        text = "Carlos Alonso",  // Ajusta el nombre del usuario
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = "Mi perfil",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.LightGray
                    )
                }
            }
        }
        // Sección inferior con fondo blanco para las opciones
        Column(
            modifier = Modifier
                .background(Color.White) // Fondo blanco
                .fillMaxHeight()  // Ocupa el resto del espacio disponible
                .padding(8.dp)
        ) {
            DrawerItem(icon = Icons.Default.Home, label = "Inicio") {
                onDestinationClicked("home")
            }
            DrawerItem(icon = Icons.Default.Search, label = "Buscar") {
                onDestinationClicked("buscar")
            }
            DrawerItem(icon = Icons.Default.ShoppingBag, label = "Mis compras") {
                onDestinationClicked("compras")
            }
            DrawerItem(icon = Icons.Default.Notifications, label = "Notificaciones") {
                onDestinationClicked("notificaciones")
            }
            DrawerItem(icon = Icons.Default.Favorite, label = "Favoritos") {
                onDestinationClicked("favoritos")
            }
            DrawerItem(icon = Icons.Default.LocalOffer, label = "Ofertas") {
                onDestinationClicked("ofertas")
            }
            DrawerItem(icon = Icons.Default.CardGiftcard, label = "Cupones") {
                onDestinationClicked("cupones")
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja la siguiente sección hacia abajo

            // Opciones adicionales en la parte inferior
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                DrawerItem(icon = painterResource(id = R.drawable.ic_account), label = "Mi cuenta") {
                    onDestinationClicked("mi_cuenta")
                }
                DrawerItem(icon = painterResource(id = R.drawable.ic_settings), label = "Ajustes") {
                    onDestinationClicked("ajustes")
                }
            }
        }
    }
}

// Función para crear cada ítem del Drawer con `ImageVector`
@Composable
fun DrawerItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp)  // Aumenta el padding para más espacio
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Black)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, color = Color.Black)
    }
}

// Función para crear cada ítem del Drawer con `Painter`
@Composable
fun DrawerItem(
    icon: androidx.compose.ui.graphics.painter.Painter,
    label: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Icon(painter = icon, contentDescription = label, tint = Color.Black)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, color = Color.Black)
    }
}

