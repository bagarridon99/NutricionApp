package com.example.nutricionapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nutricionapp.ui.screens.*

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        // 👇 ANIMACIONES GLOBALES (Aplican a todas las pantallas)
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400) // Duración 400ms
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400)
            )
        }
    ) {

        // --- Home ---
        composable(Screen.Home.route) {
            HomeScreen(
                goGuia = { navController.navigate(Screen.Guia.route) },
                goColaciones = { navController.navigate(Screen.Colaciones.route) },
                goActividad = { navController.navigate(Screen.Actividad.route) },
                goSueno = { navController.navigate(Screen.Sueno.route) }
            )
        }

        // --- Guía Alimentaria ---
        composable(Screen.Guia.route) {
            GuiaAlimentariaScreen(onBack = { navController.popBackStack() })
        }

        // --- Colaciones ---
        composable(Screen.Colaciones.route) {
            ColacionesScreen(
                onBack = { navController.popBackStack() },
                onChat = { navController.navigate(Screen.Chat.createRoute("colaciones")) }
            )
        }

        // --- Actividad Física ---
        composable(Screen.Actividad.route) {
            ActividadFisicaScreen(
                onBack = { navController.popBackStack() },
                onChat = { navController.navigate(Screen.Chat.createRoute("actividad")) }
            )
        }

        // --- Sueño ---
        composable(Screen.Sueno.route) {
            SuenoScreen(
                onBack = { navController.popBackStack() },
                onChat = { navController.navigate(Screen.Chat.createRoute("sueno")) }
            )
        }

        // --- Chat (Animación especial: Sube desde abajo) ---
        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument("type") { type = NavType.StringType }),
            // Sobrescribimos la animación SOLO para el chat para que parezca un "Modal"
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(500))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(500))
            }
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "asistente"
            ChatScreen(
                onBack = { navController.popBackStack() },
                tipoPantalla = type
            )
        }
    }
}