package com.example.cafeteriainteligente.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CarritoViewModel : ViewModel() {
    // Lista de productos en el carrito
    var productosEnCarrito = mutableStateListOf<Product>()
        private set

    // Función para agregar productos
    fun agregarProducto(product: Product) {
        if (!productosEnCarrito.contains(product)) {
            productosEnCarrito.add(product)
        }
    }

    // Función para eliminar productos
    fun eliminarProducto(product: Product) {
        productosEnCarrito.remove(product)
    }

    // Obtener la cantidad de productos en el carrito
    fun obtenerCantidadProductos(): Int {
        return productosEnCarrito.size
    }

    // Función para verificar si un producto está en el carrito
    fun estaEnCarrito(product: Product): Boolean {
        return productosEnCarrito.contains(product)
    }
}

