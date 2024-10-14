package com.example.cafeteriainteligente.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cafeteriainteligente.models.CarritoViewModel
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.*

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
                cartProducts = cartProducts,  // Asegúrate de pasar cartProducts aquí
                onAddToCart = { product ->
                    val alreadyInCart = carritoViewModel.estaEnCarrito(product)
                    if (alreadyInCart) {
                        println("Ya se agregó este producto a tu carrito")
                    } else {
                        onUpdateCart(product)  // Solo agregar el producto sin redirigir
                        carritoViewModel.agregarProducto(product)  // También agregar al ViewModel
                    }
                },
                onRemoveFromCart = { product ->  // Se agrega la función onRemoveFromCart
                    carritoViewModel.eliminarProducto(product)  // Eliminar del ViewModel
                    onUpdateCart(product)  // También manejar la eliminación en la lista global
                },
                carritoViewModel = carritoViewModel  // Pasar el ViewModel a HomeScreen
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
                    carritoViewModel.eliminarProducto(product)  // Eliminar del ViewModel
                    onUpdateCart(product)  // También manejar la eliminación en la lista global
                },
                onSaveProduct = { product ->
                    // Aquí puedes guardar el producto para más tarde (implementación futura)
                },
                carritoViewModel = carritoViewModel  // Pasar el ViewModel a CarritoScreenOnly
            )
        }
    }
}







