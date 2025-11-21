package com.example.nutricionapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Guia : Screen("guia")
    object Colaciones : Screen("colaciones")
    object Actividad : Screen("actividad")
    object Sueno : Screen("sueno")

    // 👇 Nueva ruta para el chat con argumento
    object Chat : Screen("chat/{type}") {
        fun createRoute(type: String) = "chat/$type"
    }
}