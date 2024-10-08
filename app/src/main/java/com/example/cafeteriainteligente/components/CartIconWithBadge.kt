package com.example.cafeteriainteligente.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.cafeteriainteligente.R
import androidx.compose.material3.Text  // Asegúrate de tener esta importación

@Composable
fun CartIconWithBadge(productCount: Int, onClick: () -> Unit) {
    Box {
        BadgedBox(
            badge = {
                if (productCount > 0) {
                    Badge(
                        containerColor = Color.Red
                    ) {
                        Text(text = productCount.toString())
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = "Carrito de compras"
            )
        }
    }
}
