package com.example.cafeteriainteligente.data

data class Cupon(
    val title: String,
    val description: String,
    val details: String,
    val imageUrl: String? = null // URL de la imagen del cupón (o icono)
)

fun getCuponesList(): List<Cupon> {
    return listOf(
        Cupon(
            title = "S/ 5 OFF en Café Americano",
            description = "Compra mínima S/ 10",
            details = "Descuento válido hasta el 31 de octubre de 2024 en la compra de cualquier café americano.",
            imageUrl = "https://example.com/imagenes/cafe_americano.png"
        ),
        Cupon(
            title = "2x1 en Capuchino",
            description = "Compra mínima S/ 15",
            details = "Compra un Capuchino y recibe otro gratis. Válido hasta el 15 de noviembre de 2024.",
            imageUrl = "https://example.com/imagenes/capuchino.png"
        ),
        Cupon(
            title = "15% de descuento en Sandwiches",
            description = "Compra mínima S/ 20",
            details = "Aplica en todos los sandwiches de la cafetería. Válido hasta el 10 de diciembre de 2024.",
            imageUrl = "https://example.com/imagenes/sandwich.png"
        ),
        Cupon(
            title = "S/ 3 OFF en Muffins",
            description = "Compra mínima S/ 5",
            details = "Descuento de S/ 3 en cualquier muffin. Válido hasta el 30 de noviembre de 2024.",
            imageUrl = "https://example.com/imagenes/muffin.png"
        ),
        Cupon(
            title = "10% de descuento en Menú del Día",
            description = "Compra mínima S/ 20",
            details = "Aplica en la compra de cualquier Menú del Día. Válido hasta el 25 de diciembre de 2024.",
            imageUrl = "https://example.com/imagenes/menu_del_dia.png"
        )
    )
}
