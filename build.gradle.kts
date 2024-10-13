// Archivo build.gradle.kts a nivel de proyecto

buildscript {
    dependencies {
        // Asegúrate de usar la versión 8.5.1 del Android Gradle Plugin
        classpath("com.android.tools.build:gradle:8.5.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
