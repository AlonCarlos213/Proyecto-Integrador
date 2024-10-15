package com.example.cafeteriainteligente.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.*
import com.google.gson.Gson

@Composable
fun AppNavigation(
    navController: NavHostController,
    cartProducts: List<Product>,  // Este parámetro se debe pasar correctamente
    onUpdateCart: (Product) -> Unit,  // Función para actualizar el carrito (añadir/eliminar)
    carritoViewModel: CarritoViewModel  // Pasar el ViewModel del carrito
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                navController = navController,
                cartProducts = cartProducts,
                onAddToCart = { product ->
                    val alreadyInCart = carritoViewModel.estaEnCarrito(product)
                    if (alreadyInCart) {
                        println("Ya se agregó este producto a tu carrito")
                    } else {
                        onUpdateCart(product)
                        carritoViewModel.agregarProducto(product)
                    }
                },
                onRemoveFromCart = { product ->
                    carritoViewModel.eliminarProducto(product)
                },
                carritoViewModel = carritoViewModel
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
                products = carritoViewModel.productosEnCarrito,
                onBackPressed = { navController.popBackStack() },
                onRemoveProduct = { product -> carritoViewModel.eliminarProducto(product) },
                onSaveProduct = { product -> /* Lógica de guardar */ },
                carritoViewModel = carritoViewModel,
                navController = navController  // Pasando el NavController correctamente
            )
        }
        composable(route = "carrito") {
            CarritoScreenOnly(
                products = carritoViewModel.productosEnCarrito, // Pasar los productos del carrito directamente
                onBackPressed = { navController.popBackStack() }, // Navegar de regreso a la pantalla anterior
                onRemoveProduct = { product -> carritoViewModel.eliminarProducto(product) }, // Función para eliminar productos
                onSaveProduct = { product -> /* Aquí la lógica para guardar el producto si la tienes */ },
                carritoViewModel = carritoViewModel, // Pasar el ViewModel correctamente
                navController = navController // Pasar el controlador de navegación correctamente
            )
        }

        composable(route = "payment") {
            PaymentScreen(
                navController = navController,
                carritoViewModel = carritoViewModel,
                selectedProducts = carritoViewModel.productosEnCarrito,
                onPaymentSuccess = {
                    navController.navigate("purchase_summary")
                }
            )
        }
        composable(route = "purchase_summary/{totalAmount}/{pointsEarned}") { backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getString("totalAmount")?.toDouble() ?: 0.0
            val pointsEarned = backStackEntry.arguments?.getString("pointsEarned")?.toInt() ?: 0
            PurchaseSummaryScreen(
                navController = navController,
                totalAmount = totalAmount,
                pointsEarned = pointsEarned
            )
        }
    }
}









