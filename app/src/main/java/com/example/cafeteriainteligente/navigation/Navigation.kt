package com.example.cafeteriainteligente.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    cartProducts: List<Product>,  // Este parámetro se debe pasar correctamente
    onUpdateCart: (Product) -> Unit
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                navController = navController,
                cartProducts = cartProducts,  // Asegúrate de pasar `cartProducts` aquí
                onAddToCart = { product ->
                    val alreadyInCart = cartProducts.any { it.id == product.id }
                    if (alreadyInCart) {
                        println("Ya se agregó este producto a tu carrito")
                    } else {
                        onUpdateCart(product)  // Solo agregar el producto sin redirigir
                    }
                }
            )
        }

        composable(route = "buscar") { SearchScreen() }
        composable(route = "compras") { MisComprasScreen() }
        composable(route = "notificaciones") { NotificacionesScreen() }
        composable(route = "favoritos") { FavoritosScreen() }
        composable(route = "ofertas") { OfertasScreen() }
        composable(route = "cupones") { CuponesScreen() }
        composable(route = "mi_cuenta") { MiCuentaScreen() }
        composable(route = "ajustes") { SettingsScreen() }

        composable(route = "carrito") {
            CarritoScreenOnly(
                products = cartProducts,  // Pasar lista de productos al carrito
                onBackPressed = { navController.popBackStack() },
                onRemoveProduct = { product ->
                    // Aquí puedes eliminar el producto del carrito
                    // Por ejemplo, podrías llamar a onUpdateCart con una lógica para eliminar
                },
                onSaveProduct = { product ->
                    // Aquí puedes guardar el producto para más tarde (implementación futura)
                }
            )
        }

    }
}




