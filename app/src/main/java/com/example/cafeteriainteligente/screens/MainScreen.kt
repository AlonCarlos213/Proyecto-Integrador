package com.example.cafeteriainteligente.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.cafeteriainteligente.CarritoActivity
import com.example.cafeteriainteligente.models.Product
import androidx.compose.foundation.layout.Box


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val searchText = remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Lista de productos del carrito usando rememberSaveable para mantener el estado
    var cartProducts by rememberSaveable { mutableStateOf(listOf<Product>()) }

    // Corregir el uso de `derivedStateOf` con `remember`
    val productCount by remember { derivedStateOf { cartProducts.size } }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                NavigationDrawer(
                    onDestinationClicked = { route ->
                        navController.navigate(route)
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
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
                        // Reemplazar el Box con BadgedBox para mostrar el contador de productos
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
                            cartProducts = cartProducts + newProduct
                        }
                    )
                }
            }
        )
    }
}














