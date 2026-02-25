package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PropertyCreateScreen(
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
    onBackToProperties: () -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var bedrooms by remember { mutableStateOf("") }
    var bathrooms by remember { mutableStateOf("") }
    var success by remember { mutableStateOf(false) }
    val selectedServices = remember { mutableStateListOf<String>() }

    val services = listOf("WiFi", "Parqueadero", "Piscina", "Gimnasio", "A/C", "Mascotas")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("PROPERTY CREATE 2026", color = Color(0xFFDA9C5F), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("Publicar Propiedad", color = Color(0xFFFFF4E8), fontSize = 31.sp, fontWeight = FontWeight.ExtraBold)
                Text("Crea tu publicación con estilo premium y animaciones modernas.", color = Color(0xFFD4C5B9), fontSize = 12.sp)
            }

            item {
                AnimatedVisibility(success, enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 3 })) {
                    Card(colors = CardDefaults.cardColors(containerColor = Color(0x2232CD72)), shape = RoundedCornerShape(14.dp)) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF2ECC71))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Propiedad creada correctamente", color = Color(0xFFE5FFE9), fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            item {
                SectionCard("Información básica", Icons.Default.Sell) {
                    Field("Título", title) { title = it }
                    Field("Descripción", description) { description = it }
                    Field("Precio mensual", price) { price = it }
                }
            }

            item {
                SectionCard("Ubicación", Icons.Default.LocationOn) {
                    Field("Dirección", address) { address = it }
                    Field("Ciudad", city) { city = it }
                }
            }

            item {
                SectionCard("Características", Icons.Default.AddLocationAlt) {
                    Field("Habitaciones", bedrooms, leading = { Icon(Icons.Default.Bed, null) }) { bedrooms = it }
                    Field("Baños", bathrooms, leading = { Icon(Icons.Default.Bathtub, null) }) { bathrooms = it }
                }
            }

            item {
                SectionCard("Servicios", Icons.Default.Check) {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        services.forEach { service ->
                            val selected = selectedServices.contains(service)
                            Row(
                                modifier = Modifier
                                    .background(if (selected) Color(0x44DA9C5F) else Color(0x22FFFFFF), RoundedCornerShape(50))
                                    .clickable {
                                        if (selected) selectedServices.remove(service) else selectedServices.add(service)
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(if (selected) Icons.Default.Check else Icons.Default.Close, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(service, color = Color(0xFFF0E5DB), fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            item {
                SectionCard("Imágenes", Icons.Default.Image) {
                    Text("Carga de imágenes simulada (UI lista para integrar lógica real)", color = Color(0xFFBFAFA2), fontSize = 12.sp)
                }
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    AppActionButton(
                        text = "Cancelar",
                        onClick = onBackToProperties,
                        modifier = Modifier.weight(1f),
                        gradient = listOf(Color(0xFF64748B), Color(0xFF475569), Color(0xFF64748B))
                    )
                    AppActionButton(
                        text = "Guardar",
                        onClick = { success = true },
                        modifier = Modifier.weight(1f),
                        gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F))
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
private fun SectionCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, content: @Composable Column.() -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)), shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = Color(0xFFDA9C5F))
                Spacer(modifier = Modifier.width(6.dp))
                Text(title, color = Color(0xFFFFE7C7), fontWeight = FontWeight.Bold)
            }
            content()
        }
    }
}

@Composable
private fun Field(label: String, value: String, leading: @Composable (() -> Unit)? = null, onChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = Color(0xFFD4C5B9), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            leadingIcon = leading,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
