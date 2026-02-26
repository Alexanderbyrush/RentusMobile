package com.example.rentusmobile.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.rentusmobile.domain.model.Property
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar
import com.example.rentusmobile.presentation.properties.PropertiesViewModel

@Composable
fun PropertiesScreen(
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
    onNavigatePropertyCreate: () -> Unit = {},
    onNavigatePropertyDetail: (Int) -> Unit = {},
    onNavigatePropertyEdit: (Int) -> Unit = {}
) {
    val vm: PropertiesViewModel = viewModel(factory = PropertiesViewModel.Factory(ServiceLocator.propertiesRepository))
    val state by vm.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17))))) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 84.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text("Propiedades", color = Color.White, fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = state.query,
                    onValueChange = { vm.onQueryChange(it); vm.loadProperties() },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar por título, ciudad o tipo") }
                )
            }
            item {
                AppActionButton(text = "Crear propiedad", onClick = onNavigatePropertyCreate, modifier = Modifier.fillMaxWidth())
            }
            when {
                state.isLoading -> item { Text("Cargando propiedades...", color = Color(0xFFE8DAC8)) }
                state.error != null -> item {
                    Column {
                        Text(state.error ?: "Error", color = Color(0xFFFFB4AB))
                        AppActionButton(text = "Reintentar", onClick = vm::loadProperties, modifier = Modifier.fillMaxWidth())
                    }
                }
                state.isEmpty -> item { Text("No hay propiedades disponibles", color = Color(0xFFE8DAC8)) }
                else -> items(state.data) { property ->
                    PropertyItem(
                        property = property,
                        onDetail = { onNavigatePropertyDetail(property.id) },
                        onEdit = { onNavigatePropertyEdit(property.id) }
                    )
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

@Composable
private fun PropertyItem(property: Property, onDetail: () -> Unit, onEdit: () -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(property.title, color = Color.White, fontWeight = FontWeight.Bold)
            Text("${property.city} · ${property.price}", color = Color(0xFFD4C5B9))
            Text("${property.bedrooms} · ${property.bathrooms} · ${property.area}", color = Color(0xFFD4C5B9))
            Text("Ver detalle", color = Color(0xFFDA9C5F), modifier = Modifier.clickable(onClick = onDetail))
            Text("Editar", color = Color(0xFF9FD18B), modifier = Modifier.clickable(onClick = onEdit))
        }
    }
}
