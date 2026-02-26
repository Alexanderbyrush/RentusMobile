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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentusmobile.core.network.ServiceLocator
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar
import com.example.rentusmobile.presentation.properties.PropertyFormViewModel

@Composable
fun PropertyCreateScreen(
    onNavigateHome: () -> Unit = {}, onNavigateProperties: () -> Unit = {}, onNavigateAbout: () -> Unit = {},
    onNavigateProfile: () -> Unit = {}, onNavigateNotifications: () -> Unit = {}, onNavigateContracts: () -> Unit = {},
    onNavigatePayments: () -> Unit = {}, onNavigateMaintenance: () -> Unit = {}, onNavigateMyRequests: () -> Unit = {},
    onNavigateRequests: () -> Unit = {}, onNavigateMyReports: () -> Unit = {}, onNavigateSettings: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    PropertyFormScaffold(isEdit = false, onNavigateBack = onNavigateBack, onNavigateHome = onNavigateHome, onNavigateProperties = onNavigateProperties,
        onNavigateAbout = onNavigateAbout, onNavigateProfile = onNavigateProfile, onNavigateNotifications = onNavigateNotifications,
        onNavigateContracts = onNavigateContracts, onNavigatePayments = onNavigatePayments, onNavigateMaintenance = onNavigateMaintenance,
        onNavigateMyRequests = onNavigateMyRequests, onNavigateRequests = onNavigateRequests, onNavigateMyReports = onNavigateMyReports,
        onNavigateSettings = onNavigateSettings)
}

@Composable
internal fun PropertyFormScaffold(
    isEdit: Boolean,
    propertyId: Int? = null,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateProperties: () -> Unit,
    onNavigateAbout: () -> Unit,
    onNavigateProfile: () -> Unit,
    onNavigateNotifications: () -> Unit,
    onNavigateContracts: () -> Unit,
    onNavigatePayments: () -> Unit,
    onNavigateMaintenance: () -> Unit,
    onNavigateMyRequests: () -> Unit,
    onNavigateRequests: () -> Unit,
    onNavigateMyReports: () -> Unit,
    onNavigateSettings: () -> Unit,
) {
    val vm: PropertyFormViewModel = viewModel(factory = PropertyFormViewModel.Factory(ServiceLocator.propertiesRepository))
    val state by vm.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17))))) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp).padding(bottom = 84.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item { Text(if (isEdit) "Editar propiedad" else "Crear propiedad", color = Color.White) }
            item { FormField("Título", state.title) { vm.onFieldChange { copy(title = it) } } }
            item { FormField("Descripción", state.description) { vm.onFieldChange { copy(description = it) } } }
            item { FormField("Ciudad", state.city) { vm.onFieldChange { copy(city = it) } } }
            item { FormField("Dirección", state.location) { vm.onFieldChange { copy(location = it) } } }
            item { FormField("Precio", state.price) { vm.onFieldChange { copy(price = it) } } }
            item { FormField("Habitaciones", state.bedrooms) { vm.onFieldChange { copy(bedrooms = it) } } }
            item { FormField("Baños", state.bathrooms) { vm.onFieldChange { copy(bathrooms = it) } } }
            item { FormField("Área", state.area) { vm.onFieldChange { copy(area = it) } } }
            state.errorMessage?.let { item { Text(it, color = Color(0xFFFFB4AB)) } }
            state.successMessage?.let { item { Text(it, color = Color(0xFF9FD18B)) } }
            item { AppActionButton(text = if (isEdit) "Guardar cambios" else "Crear", onClick = { if (isEdit) vm.update() else vm.create() }, modifier = Modifier.fillMaxWidth()) }
            item { AppActionButton(text = "Volver", onClick = onNavigateBack, modifier = Modifier.fillMaxWidth(), gradient = listOf(Color.Gray, Color.DarkGray, Color.Gray)) }
        }
        HomeNavbar(modifier = Modifier.align(Alignment.BottomCenter), selectedTab = "Propiedades", onNavigateHome = onNavigateHome,
            onNavigateProperties = onNavigateProperties, onNavigateAbout = onNavigateAbout, onNavigateProfile = onNavigateProfile,
            onNavigateNotifications = onNavigateNotifications, onNavigateContracts = onNavigateContracts, onNavigatePayments = onNavigatePayments,
            onNavigateMaintenance = onNavigateMaintenance, onNavigateMyRequests = onNavigateMyRequests, onNavigateRequests = onNavigateRequests,
            onNavigateMyReports = onNavigateMyReports, onNavigateSettings = onNavigateSettings)
    }

    if (isEdit && propertyId != null && state.id == null && !state.isLoading) {
        androidx.compose.runtime.LaunchedEffect(propertyId) { vm.loadProperty(propertyId) }
    }
}

@Composable
private fun FormField(label: String, value: String, onChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = Color(0xFFD4C5B9))
        OutlinedTextField(value = value, onValueChange = onChange, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
    }
}
