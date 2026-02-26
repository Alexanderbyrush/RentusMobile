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
import com.example.rentusmobile.presentation.screens.home.ContractsScreen
import com.example.rentusmobile.presentation.screens.home.HomeScreen
import com.example.rentusmobile.presentation.screens.home.NotificationsScreen
import com.example.rentusmobile.presentation.screens.home.MaintenanceScreen
import com.example.rentusmobile.presentation.screens.home.MyRequestsScreen
import com.example.rentusmobile.presentation.screens.home.RequestsScreen
import com.example.rentusmobile.presentation.screens.home.MyReportsScreen
import com.example.rentusmobile.presentation.screens.home.PaymentsScreen
import com.example.rentusmobile.presentation.screens.home.ProfileScreen
import com.example.rentusmobile.presentation.screens.home.PropertiesScreen
import com.example.rentusmobile.presentation.screens.home.SettingsScreen
import com.example.rentusmobile.presentation.screens.home.PropertyCreateScreen
import com.example.rentusmobile.presentation.screens.home.PropertyDetailScreen
import com.example.rentusmobile.presentation.screens.home.PropertyEditScreen

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object About : Screen("about")
    data object Properties : Screen("properties")
    data object Profile : Screen("profile")
    data object Notifications : Screen("notifications")
    data object Contracts : Screen("contracts")
    data object Payments : Screen("payments")
    data object Maintenance : Screen("maintenance")
    data object MyRequests : Screen("my_requests")
    data object Requests : Screen("requests")
    data object MyReports : Screen("my_reports")
    data object Settings : Screen("settings")
    data object PropertyCreate : Screen("property_create")
    data object PropertyDetail : Screen("property_detail/{propertyId}") {
        fun createRoute(propertyId: Int) = "property_detail/$propertyId"
    }
    data object PropertyEdit : Screen("property_edit/{propertyId}") {
        fun createRoute(propertyId: Int) = "property_edit/$propertyId"
    }
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
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.About.route) {
            AboutScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Properties.route) {
            PropertiesScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = {},
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) },
                onNavigatePropertyCreate = { navigateSingleTop(Screen.PropertyCreate.route) },
                onNavigatePropertyDetail = { id -> navigateSingleTop(Screen.PropertyDetail.createRoute(id)) },
                onNavigatePropertyEdit = { id -> navigateSingleTop(Screen.PropertyEdit.createRoute(id)) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = {},
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = {},
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Contracts.route) {
            ContractsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = {},
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Payments.route) {
            PaymentsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = {},
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Maintenance.route) {
            MaintenanceScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = {},
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.MyRequests.route) {
            MyRequestsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = {},
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Requests.route) {
            RequestsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = {},
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.MyReports.route) {
            MyReportsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = {},
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = {}
            )
        }

        composable(Screen.PropertyCreate.route) {
            PropertyCreateScreen(
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) },
                onBackToProperties = { navController.popBackStack() }
            )
        }

        composable(Screen.PropertyDetail.route) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getString("propertyId")?.toIntOrNull() ?: 0
            PropertyDetailScreen(
                propertyId = propertyId,
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) },
                onNavigateEdit = { id -> navigateSingleTop(Screen.PropertyEdit.createRoute(id)) }
            )
        }

        composable(Screen.PropertyEdit.route) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getString("propertyId")?.toIntOrNull() ?: 0
            PropertyEditScreen(
                propertyId = propertyId,
                onNavigateHome = { navigateToRootTab(Screen.Home.route) },
                onNavigateProperties = { navigateToRootTab(Screen.Properties.route) },
                onNavigateAbout = { navigateToRootTab(Screen.About.route) },
                onNavigateProfile = { navigateToRootTab(Screen.Profile.route) },
                onNavigateNotifications = { navigateToRootTab(Screen.Notifications.route) },
                onNavigateContracts = { navigateToRootTab(Screen.Contracts.route) },
                onNavigatePayments = { navigateToRootTab(Screen.Payments.route) },
                onNavigateMaintenance = { navigateToRootTab(Screen.Maintenance.route) },
                onNavigateMyRequests = { navigateToRootTab(Screen.MyRequests.route) },
                onNavigateRequests = { navigateToRootTab(Screen.Requests.route) },
                onNavigateMyReports = { navigateToRootTab(Screen.MyReports.route) },
                onNavigateSettings = { navigateToRootTab(Screen.Settings.route) },
                onBackToDetail = { navigateSingleTop(Screen.PropertyDetail.createRoute(propertyId)) }
            )
        }

    }
}
