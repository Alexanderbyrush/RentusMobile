package com.example.rentusmobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rentusmobile.presentation.screens.auth.LoginScreen
import com.example.rentusmobile.presentation.screens.auth.RegisterScreen
import com.example.rentusmobile.presentation.screens.home.AboutScreen
import com.example.rentusmobile.presentation.screens.home.HomeScreen

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object About : Screen("about")
    data object Properties : Screen("properties")
}

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onNavigateBack = {},
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onForgotPassword = {}
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onTermsClick = {}
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateHome = {},
                onNavigateProperties = { navController.navigate(Screen.Properties.route) },
                onNavigateAbout = { navController.navigate(Screen.About.route) }
            )
        }

        composable(Screen.About.route) {
            AboutScreen(
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateProperties = { navController.navigate(Screen.Properties.route) }
            )
        }

        composable(Screen.Properties.route) {
            HomeScreen(
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateProperties = {},
                onNavigateAbout = { navController.navigate(Screen.About.route) }
            )
        }
    }
}
