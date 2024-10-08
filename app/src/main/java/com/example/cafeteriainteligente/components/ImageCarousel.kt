package com.example.cafeteriainteligente.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(
    images: List<Int>,  // Lista de imágenes que se mostrarán en el carrusel
    modifier: Modifier = Modifier
) {
    // Inicializa el estado del paginador con la cantidad de imágenes
    val pagerState = rememberPagerState(pageCount = { images.size })  // Define la cantidad de páginas
    val coroutineScope = rememberCoroutineScope()  // Permite lanzar corutinas

    // Efecto lanzado para auto-scroll
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)  // Cambia la imagen cada 3 segundos
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)  // Desplazamiento suave a la siguiente página
            }
        }
    }

    // Creación del carrusel usando `HorizontalPager`
    HorizontalPager(
        state = pagerState,  // Usa el `pagerState` configurado
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Image(
            painter = painterResource(id = images[page]),  // Imagen de la lista según la posición
            contentDescription = "Imagen del carrusel",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))  // Aplicar esquinas redondeadas con un radio de 16.dp
        )
    }
}
