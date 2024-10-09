plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.cafeteriainteligente"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cafeteriainteligente"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    // Compatibilidad y componentes básicos de Android
    implementation(libs.androidx.appcompat)                     // Compatibilidad con versiones anteriores de Android
    implementation(libs.material)                               // Material Design
    implementation(libs.androidx.core.ktx)                      // Extensiones para Kotlin en AndroidX
    implementation(libs.androidx.activity)                      // Soporte para manejar actividades
    implementation(libs.androidx.constraintlayout)              // Layout de restricciones en Compose

    // Integración y ciclo de vida
    implementation(libs.androidx.lifecycle.runtime.ktx)         // Manejo del ciclo de vida en Kotlin
    implementation(libs.androidx.activity.compose)              // Soporte para actividades en Compose

    // Jetpack Compose (Administrado con BOM - Bill of Materials)
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00") // BOM para las versiones de Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)                            // Módulos de UI de Compose
    implementation(libs.androidx.ui.graphics)                   // Soporte de gráficos en Compose
    implementation(libs.androidx.ui.tooling.preview)            // Herramienta de previsualización de UI en Compose
    implementation(libs.androidx.material3)                     // Componentes de Material 3 para Compose

    // Navegación en Compose
    implementation("androidx.navigation:navigation-compose:2.6.0") // Navegación entre pantallas en Compose
    implementation("androidx.compose.material:material-icons-extended:1.5.1") // Íconos extendidos de Material Design

    // Herramientas de desarrollo para la previsualización
    debugImplementation("androidx.compose.ui:ui-tooling")       // Previsualización de la UI en tiempo de ejecución
    implementation("androidx.compose.ui:ui-tooling-preview")    // Previsualización de UI en el editor
    implementation("androidx.compose.ui:ui:1.5.1")
    // Bibliotecas para carruseles y controles de UI adicionales
    implementation("androidx.compose.foundation:foundation:1.5.1")  // Fundamentos de la UI en Compose
         // Material Design en Compose
    implementation("androidx.compose.material3:material3:1.0.1")     // Material Design 3

    // Soporte para carrusel de imágenes con `accompanist`
    implementation("com.google.accompanist:accompanist-pager:0.30.0")               // Biblioteca para carruseles (actualizado)
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.0")   // Indicadores de páginas en carrusel
    implementation("androidx.compose.runtime:runtime:1.5.1")
    // Dependencias de prueba
    testImplementation(libs.junit)                              // Pruebas unitarias
    androidTestImplementation(libs.androidx.junit)              // Pruebas de UI con JUnit para Android
    androidTestImplementation(libs.androidx.espresso.core)      // Pruebas de UI con Espresso
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM para pruebas en Compose
    androidTestImplementation(libs.androidx.ui.test.junit4)     // Pruebas de UI en Compose con JUnit
    debugImplementation(libs.androidx.ui.test.manifest)         // Pruebas de UI en modo debug
}

