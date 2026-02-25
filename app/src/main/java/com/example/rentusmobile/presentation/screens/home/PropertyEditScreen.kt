package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun PropertyEditScreen(
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
    var title by remember { mutableStateOf("Penthouse Sky Lounge") }
    var description by remember { mutableStateOf("Vista panorámica y diseño minimalista 2026") }
    var price by remember { mutableStateOf("6200000") }
    var city by remember { mutableStateOf("Medellín") }
    var saved by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 14.dp).padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("PROPERTY EDIT", color = Color(0xFFDA9C5F), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Edit, null, tint = Color(0xFFDA9C5F))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Editar propiedad", color = Color(0xFFFFF4E8), fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                }
            }

            item {
                AnimatedVisibility(saved, enter = fadeIn()) {
                    Card(colors = CardDefaults.cardColors(containerColor = Color(0x222ECC71)), shape = RoundedCornerShape(14.dp)) {
                        Text("Cambios guardados correctamente", modifier = Modifier.padding(12.dp), color = Color(0xFF98F5C0), fontWeight = FontWeight.Bold)
                    }
                }
            }

            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)), shape = RoundedCornerShape(16.dp)) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Información básica", color = Color(0xFFFFE7C7), fontWeight = FontWeight.Bold)
                        EditField("Título", title) { title = it }
                        EditField("Descripción", description) { description = it }
                        EditField("Precio mensual", price) { price = it }
                        EditField("Ciudad", city) { city = it }
                    }
                }
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    AppActionButton(
                        text = "Cancelar",
                        onClick = onBackToDetail,
                        modifier = Modifier.weight(1f),
                        gradient = listOf(Color(0xFF64748B), Color(0xFF475569), Color(0xFF64748B))
                    )
                    AppActionButton(
                        text = "Guardar",
                        onClick = { saved = true },
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
private fun EditField(label: String, value: String, onChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = Color(0xFFD4C5B9), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
