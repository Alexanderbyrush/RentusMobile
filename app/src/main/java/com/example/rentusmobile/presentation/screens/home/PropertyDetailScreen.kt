package com.example.rentusmobile.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentusmobile.core.network.ServiceLocator
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar
import com.example.rentusmobile.presentation.properties.PropertyDetailViewModel

@Composable
fun PropertyDetailScreen(
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
    onNavigateEdit: (Int) -> Unit = {}
) {
    val vm: PropertyDetailViewModel = viewModel(factory = PropertyDetailViewModel.Factory(ServiceLocator.propertiesRepository))
    val state by vm.uiState.collectAsState()

    LaunchedEffect(propertyId) { vm.load(propertyId) }

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17))))) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp).padding(bottom = 84.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            when {
                state.isLoading -> item { Text("Cargando detalle...", color = Color.White) }
                state.error != null -> item { Text(state.error ?: "Error", color = Color(0xFFFFB4AB)) }
                state.isEmpty || state.data == null -> item { Text("Propiedad no encontrada", color = Color.White) }
                else -> {
                    val property = state.data!!
                    item {
                        Card(colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(property.title, color = Color.White, fontWeight = FontWeight.Bold)
                                Text(property.description, color = Color(0xFFD4C5B9))
                                Text("${property.city} - ${property.location}", color = Color(0xFFD4C5B9))
                                Text(property.price, color = Color(0xFFDA9C5F), fontWeight = FontWeight.Bold)
                                Text("Vistas: ${property.viewsCount}", color = Color(0xFFD4C5B9))
                            }
                        }
                    }
                    item { AppActionButton(text = "Editar", onClick = { onNavigateEdit(property.id) }, modifier = Modifier.fillMaxWidth()) }
                }
            }
        }

        HomeNavbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedTab = "Propiedades",
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
}
