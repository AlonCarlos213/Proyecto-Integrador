package com.example.cafeteriainteligente.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cafeteriainteligente.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import android.app.Activity

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    // Configuración de Google Sign-In
    val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))  // Reemplaza con tu ID de cliente web
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

    // Manejar el resultado del login con Google
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken ?: "", auth, navController)
            } catch (e: ApiException) {
                // Manejar el error si la autenticación falla
                e.printStackTrace()
            }
        }
    }

    // UI de la pantalla de login
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1ACD81)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo y Nombre del Grupo
            Image(
                painter = painterResource(id = R.drawable.logo_enzi),
                contentDescription = "Logo Enzi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.White, CircleShape)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de inicio de sesión con Google
            Button(onClick = { launcher.launch(googleSignInClient.signInIntent) }) {
                Text("Iniciar Sesión con Google")
            }
        }
    }
}

private fun firebaseAuthWithGoogle(idToken: String, auth: FirebaseAuth, navController: NavController) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Usuario autenticado, navegar a la pantalla principal
                navController.navigate("home")
            } else {
                // Fallo en la autenticación
                task.exception?.printStackTrace()
            }
        }
    }

