// Product.kt
package com.example.cafeteriainteligente.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,  // Identificador único
    val imageRes: Int,
    val name: String,
    val price: String
) : Parcelable
