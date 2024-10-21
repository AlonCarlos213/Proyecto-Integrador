package com.example.cafeteriainteligente.screens

import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cafeteriainteligente.R
import com.example.cafeteriainteligente.navigation.AppNavigation
import com.example.cafeteriainteligente.navigation.NavigationDrawer
import com.example.cafeteriainteligente.components.SearchBar
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafeteriainteligente.models.CarritoViewModel
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.CarritoActivity
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val navController = rememberNavController()
    val searchText = remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Simulando los valores de usuario
    val user = FirebaseAuth.getInstance().currentUser
    val profileImage: Bitmap? = null // Aquí puedes pasar la imagen del perfil desde tu ViewModel o lógica
    val userName = user?.displayName ?: "Usuario Invitado"

    // Obtener el ViewModel del carrito
    val carritoViewModel: CarritoViewModel = viewModel()

    // Obtener los productos del carrito desde el ViewModel
    val cartProducts by remember { derivedStateOf { carritoViewModel.productosEnCarrito } }

    // Corregir el uso de `derivedStateOf` con `remember`
    val productCount by remember { derivedStateOf { carritoViewModel.productosEnCarrito.size } }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                // Pasamos los valores correctos a `NavigationDrawer`
                NavigationDrawer(
                    onDestinationClicked = { route ->
                        navController.navigate(route)
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    profileImage = profileImage,  // Pasar la imagen del perfil
                    userName = userName           // Pasar el nombre del usuario
                )
            }
        },
        scrimColor = Color(0x804CAF50)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        SearchBar(
                            query = searchText.value,
                            onQueryChange = { searchText.value = it },
                            onSearch = { /* Acción de búsqueda */ },
                            modifier = Modifier
                                .fillMaxWidth(0.99f)
                                .height(45.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = "Menú"
                            )
                        }
                    },
                    actions = {
                        BadgedBox(
                            badge = {
                                if (productCount > 0) {
                                    Badge(
                                        containerColor = Color.Red
                                    ) {
                                        Text(text = productCount.toString(), color = Color.White)
                                    }
                                }
                            }
                        ) {
                            IconButton(onClick = {
                                val intent = Intent(context, CarritoActivity::class.java).apply {
                                    putParcelableArrayListExtra("products", ArrayList(cartProducts))
                                }
                                context.startActivity(intent)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_shopping_cart),
                                    contentDescription = "Carrito de compras"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    AppNavigation(
                        navController = navController,
                        cartProducts = cartProducts,
                        onUpdateCart = { newProduct ->
                            carritoViewModel.agregarProducto(newProduct)
                        },
                        carritoViewModel = carritoViewModel
                    )
                }
            }
        )
    }
}













