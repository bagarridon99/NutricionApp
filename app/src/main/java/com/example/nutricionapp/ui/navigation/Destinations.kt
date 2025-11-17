package com.example.nutricionapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Guia : Screen("guia")
    object Colaciones : Screen("colaciones")
    object Actividad : Screen("actividad")
    object Sueno : Screen("sueno")
}
