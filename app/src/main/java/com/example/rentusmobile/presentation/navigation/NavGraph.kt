package com.example.rentusmobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rentusmobile.presentation.screens.auth.LoginScreen
import com.example.rentusmobile.presentation.screens.auth.RegisterScreen
import com.example.rentusmobile.presentation.screens.home.AboutScreen
import com.example.rentusmobile.presentation.screens.home.HomeScreen
import com.example.rentusmobile.presentation.screens.home.ProfileScreen
import com.example.rentusmobile.presentation.screens.home.PropertiesScreen

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object About : Screen("about")
    data object Properties : Screen("properties")
    data object Profile : Screen("profile")
}

@Composable
fun NavGraph(
    navController: NavHostController
) {
    fun navigateSingleTop(route: String, builder: NavOptionsBuilder.() -> Unit = {}) {
        navController.navigate(route) {
            launchSingleTop = true
            builder()
        }
    }

    fun navigateToRootTab(route: String) {
        navigateSingleTop(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navigateSingleTop(Screen.Register.route) },
                onNavigateBack = { navController.popBackStack() },
                onLoginSuccess = {
                    navigateSingleTop(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onForgotPassword = {}
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navigateSingleTop(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onTermsClick = {}
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateHome = {},
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) }
            )
        }

        composable(Screen.About.route) {
            AboutScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) }
            )
        }

        composable(Screen.Properties.route) {
            PropertiesScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = {},
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = {}
            )
        }
    }
}
