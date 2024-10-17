package com.example.cafeteriainteligente.data

import com.example.cafeteriainteligente.R

data class Oferta(
    val title: String,
    val description: String,
    val imageResId: Int,
    val details: String
)

fun getOfertasList(): List<Oferta> {
    return listOf(
        Oferta(
            title = "Descuento en Café Americano",
            description = "Hasta 15% OFF en Café Americano",
            imageResId = R.drawable.cafe,
            details = "Disfruta de un delicioso café americano con un 15% de descuento. Oferta válida de 8:00 a.m. a 10:00 a.m."
        ),
        Oferta(
            title = "Promoción 2x1 en Capuchino",
            description = "Compra 1 Capuchino y lleva otro gratis",
            imageResId = R.drawable.bebidas,
            details = "Ideal para compartir con un amigo. Llévate un capuchino adicional al comprar uno."
        ),
        Oferta(
            title = "20% de descuento en Sandwiches",
            description = "Descuento en todos los tipos de sandwiches",
            imageResId = R.drawable.sandwich,
            details = "Obtén un 20% de descuento en nuestro menú de sandwiches. Oferta válida todo el día."
        ),
        Oferta(
            title = "S/ 3 OFF en Muffins",
            description = "Ahorra S/ 3 al comprar cualquier muffin",
            imageResId = R.drawable.dulces,
            details = "Disfruta de nuestros muffins de sabores variados con un descuento especial de S/ 3."
        ),
        Oferta(
            title = "10% de descuento en Menú del Día",
            description = "Descuento en el menú completo",
            imageResId = R.drawable.menu1,
            details = "Aprovecha esta oferta en nuestro menú del día, con una selección especial de platos caseros."
        )
    )
}
