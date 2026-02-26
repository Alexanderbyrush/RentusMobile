package com.example.rentusmobile.presentation.screens.home

import androidx.compose.runtime.Composable

@Composable
fun PropertyEditScreen(
    propertyId: Int,
    onNavigateHome: () -> Unit = {},
    onNavigateProperties: () -> Unit = {},
    onNavigateAbout: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
    onNavigateContracts: () -> Unit = {},
    onNavigatePayments: () -> Unit = {},
    onNavigateMaintenance: () -> Unit = {},
    onNavigateMyRequests: () -> Unit = {},
    onNavigateRequests: () -> Unit = {},
    onNavigateMyReports: () -> Unit = {},
    onNavigateSettings: () -> Unit = {},
    onBackToDetail: () -> Unit = {}
) {
    PropertyFormScaffold(
        isEdit = true,
        propertyId = propertyId,
        onNavigateBack = onBackToDetail,
        onNavigateHome = onNavigateHome,
        onNavigateProperties = onNavigateProperties,
        onNavigateAbout = onNavigateAbout,
        onNavigateProfile = onNavigateProfile,
        onNavigateNotifications = onNavigateNotifications,
        onNavigateContracts = onNavigateContracts,
        onNavigatePayments = onNavigatePayments,
        onNavigateMaintenance = onNavigateMaintenance,
        onNavigateMyRequests = onNavigateMyRequests,
        onNavigateRequests = onNavigateRequests,
        onNavigateMyReports = onNavigateMyReports,
        onNavigateSettings = onNavigateSettings
    )
}
