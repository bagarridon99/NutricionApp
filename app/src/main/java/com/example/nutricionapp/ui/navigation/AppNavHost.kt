package com.example.nutricionapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nutricionapp.ui.screens.ActividadFisicaScreen
import com.example.nutricionapp.ui.screens.ColacionesScreen
import com.example.nutricionapp.ui.screens.GuiaAlimentariaScreen
import com.example.nutricionapp.ui.screens.HomeScreen
import com.example.nutricionapp.ui.screens.SuenoScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // Empezamos en la pantalla Home
    ) {

        // --- Pantalla Principal ---
        composable(Screen.Home.route) {
            HomeScreen(
                // Definimos las acciones de navegación
                goGuia = { navController.navigate(Screen.Guia.route) },
                goColaciones = { navController.navigate(Screen.Colaciones.route) },
                goActividad = { navController.navigate(Screen.Actividad.route) },
                goSueno = { navController.navigate(Screen.Sueno.route) }
            )
        }

        // --- Pantalla Guía Alimentaria ---
        composable(Screen.Guia.route) {
            GuiaAlimentariaScreen(
                onBack = { navController.popBackStack() } // Acción para volver atrás
            )
        }

        // --- Pantalla Colaciones ---
        composable(Screen.Colaciones.route) {
            ColacionesScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // --- Pantalla Actividad Física ---
        composable(Screen.Actividad.route) {
            ActividadFisicaScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // --- Pantalla Sueño ---
        composable(Screen.Sueno.route) {
            SuenoScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}