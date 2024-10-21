package com.example.cafeteriainteligente.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiCuentaScreen(onNavigateToHome: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var profileImage by remember { mutableStateOf<Bitmap?>(null) }
    var isUpdating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Cargar la imagen de perfil guardada
    LaunchedEffect(Unit) {
        profileImage = loadProfileImage(context)
    }

    // Lanzador para la galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                val bitmap = context.contentResolver.loadThumbnail(it, android.util.Size(128, 128), null)
                profileImage = bitmap
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Cuenta") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToHome() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0FA968), // Color verde Jade
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (user != null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    // Mostrar imagen de perfil
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color.Gray, CircleShape)
                            .clickable { galleryLauncher.launch("image/*") } // Lanzar galería al hacer clic
                    ) {
                        profileImage?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Foto de perfil",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } ?: run {
                            Text("Seleccionar foto")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { galleryLauncher.launch("image/*") }) {
                        Text("Elegir de la galería")
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Información del usuario
                    Text(text = "Nombre: ${user.displayName ?: "No disponible"}")
                    Text(text = "Correo: ${user.email ?: "No disponible"}")

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botón para actualizar perfil con animación de carga
                    Button(onClick = {
                        coroutineScope.launch {
                            isUpdating = true
                            profileImage?.let {
                                saveProfileImage(context, it)
                            }
                            isUpdating = false
                        }
                    }) {
                        Text("Actualizar perfil")
                    }

                    if (isUpdating) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                        Text("Actualizando perfil...")
                    }
                }
            } else {
                Text(text = "No hay un usuario autenticado.")
            }
        }
    }
}

// Función para guardar la imagen de perfil en el almacenamiento local
fun saveProfileImage(context: Context, bitmap: Bitmap) {
    val file = File(context.filesDir, "profile_image.png")
    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    outputStream.flush()
    outputStream.close()
}

// Función para cargar la imagen de perfil del almacenamiento local
fun loadProfileImage(context: Context): Bitmap? {
    val file = File(context.filesDir, "profile_image.png")
    return if (file.exists()) {
        BitmapFactory.decodeFile(file.absolutePath)
    } else {
        null
    }
}
