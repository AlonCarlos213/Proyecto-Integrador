package com.example.cafeteriainteligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.cafeteriainteligente.models.Product
import com.example.cafeteriainteligente.screens.CarritoScreenOnly

class CarritoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products = intent.getParcelableArrayListExtra<Product>("products") ?: listOf()
            CarritoScreenOnly(products = products) {
                finish()  // Cerrar actividad al presionar atrás
            }
        }
    }
}



