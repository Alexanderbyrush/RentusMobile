package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

data class MaintenanceItem(val id: Int, val date: String, val property: String, val title: String, val priority: String, val status: String)

@Composable
fun MaintenanceScreen(
    onNavigateHome: () -> Unit = {},
    onNavigateProperties: () -> Unit = {},
    onNavigateAbout: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
    onNavigateContracts: () -> Unit = {},
    onNavigatePayments: () -> Unit = {},
    onNavigateMaintenance: () -> Unit = {},
    onNavigateMyRequests: () -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    val rows = remember {
        listOf(
            MaintenanceItem(901, "2026-03-01", "Torre Alta 402", "Fuga en cocina", "high", "pending"),
            MaintenanceItem(902, "2026-03-03", "Vista Sol 1301", "Cambio de luminaria", "medium", "in_progress"),
            MaintenanceItem(903, "2026-03-05", "Gran Reserva 609", "Revisión de cerradura", "low", "completed")
        )
    }
    val filtered = rows.filter {
        it.title.contains(query, true) || it.property.contains(query, true) || it.id.toString().contains(query)
    }

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))) {
        MaintenanceBg()
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 84.dp)) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color(0x22DA9C5F)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Build, contentDescription = null, tint = Color(0xFFDA9C5F))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Mantenimiento", color = Color(0xFFF0E5DB), fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Solicitudes y estado en tiempo real", color = Color(0xFFD4C5B9), fontSize = 12.sp)
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    AppActionButton(text = "Nueva solicitud", onClick = {}, modifier = Modifier.weight(1f), contentColor = Color(0xFF1A0E0A), gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F)))
                    AppActionButton(text = "Pendientes", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFF3B251D), Color(0xFF4D2F24), Color(0xFF6C4531)))
                }

                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text("Buscar por propiedad o id") },
                    singleLine = true
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filtered) { row ->
                    Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color(0xF23A2318))) {
                        Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("#${row.id}", color = Color(0xFFDA9C5F), fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(row.date, color = Color(0xFFD4C5B9), fontSize = 12.sp)
                                Spacer(modifier = Modifier.weight(1f))
                                val statusColor = when (row.status) {
                                    "pending" -> Color(0xFFF59E0B)
                                    "in_progress" -> Color(0xFF93C5FD)
                                    else -> Color(0xFF2ECC71)
                                }
                                Text(row.status.uppercase(), color = statusColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(row.property, color = Color.White, fontWeight = FontWeight.SemiBold)
                            Text(row.title, color = Color(0xFFE5D6C8))
                            val priorityColor = when (row.priority) {
                                "high" -> Color(0xFFE74C3C)
                                "medium" -> Color(0xFFF59E0B)
                                else -> Color(0xFFA0AEC0)
                            }
                            Text("Prioridad: ${row.priority}", color = priorityColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        HomeNavbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedTab = "",
            onNavigateHome = onNavigateHome,
            onNavigateProperties = onNavigateProperties,
            onNavigateAbout = onNavigateAbout,
            onNavigateProfile = onNavigateProfile,
            onNavigateNotifications = onNavigateNotifications,
            onNavigateContracts = onNavigateContracts,
            onNavigatePayments = onNavigatePayments,
            onNavigateMaintenance = onNavigateMaintenance,
            onNavigateMyRequests = onNavigateMyRequests
        )
    }
}

@Composable
private fun MaintenanceBg() {
    val t = rememberInfiniteTransition(label = "mnt-bg")
    val shift by t.animateFloat(0f, -15f, infiniteRepeatable(tween(2200, easing = LinearEasing), RepeatMode.Reverse), label = "mnt-shift")
    Box(Modifier.fillMaxSize()) {
        repeat(10) { i ->
            Box(
                modifier = Modifier
                    .padding(start = (i * 34).dp, top = (70 + i * 45).dp + shift.dp)
                    .size((4 + i % 2).dp)
                    .clip(CircleShape)
                    .background(Color(0x44DA9C5F))
            )
        }
    }
}
