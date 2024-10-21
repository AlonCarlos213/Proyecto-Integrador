package com.example.cafeteriainteligente.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.*
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.Q)
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
                    if (!carritoViewModel.estaEnCarrito(product)) {
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
        composable(route = "ofertas") {
            OfertasScreen(
                onNavigateToHome = {
                    navController.navigate("home")
                }
            )
        }
        composable(route = "cupones") {
            CuponesScreen(onNavigateBack = {
                navController.popBackStack()  // Esto permite volver a la pantalla anterior sin reiniciar la app
            })
        }
        composable(route = "mi_cuenta") {
            MiCuentaScreen(onNavigateToHome = {
                navController.navigate("home")
            })
        }
        composable(route = "ajustes") {
            AjustesScreen(
                navController = navController,  // Pasar el navController
                onNavigateToHome = {
                    navController.navigate("home")
                }
            )
        }
        composable(route = "carrito") {
            CarritoScreenOnly(
                products = carritoViewModel.productosEnCarrito,
                onBackPressed = { navController.popBackStack() },  // Sin advertencia aquí
                onRemoveProduct = { product -> carritoViewModel.eliminarProducto(product) },
                onSaveProduct = { product -> /* Lógica de guardar */ },
                carritoViewModel = carritoViewModel,
                navController = navController
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
        composable(
            route = "purchase_summary/{totalAmount}/{pointsEarned}/{purchasedProducts}",
            arguments = listOf(
                navArgument("totalAmount") { type = NavType.StringType },
                navArgument("pointsEarned") { type = NavType.StringType },
                navArgument("purchasedProducts") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getString("totalAmount")?.toDouble() ?: 0.0
            val pointsEarned = backStackEntry.arguments?.getString("pointsEarned")?.toInt() ?: 0
            val purchasedProductsJson =
                backStackEntry.arguments?.getString("purchasedProducts") ?: "[]"

            val gson = Gson()
            val purchasedProducts: List<Product> =
                gson.fromJson(purchasedProductsJson, Array<Product>::class.java).toList()

            // Pasar carritoViewModel y otros parámetros
            PurchaseSummaryScreen(
                navController = navController,
                totalAmount = totalAmount,
                pointsEarned = pointsEarned,
                purchasedProducts = purchasedProducts,
                carritoViewModel = carritoViewModel // Asegúrate de pasar este parámetro
            )
        }
        // Nueva ruta para la pantalla de Reservar comida
        composable(route = "sugerir_plato") {
            ReservarComidaScreen(
                onNavigateBack = { navController.popBackStack() }  // Usa popBackStack para retroceder
            )
        }
    }
}









