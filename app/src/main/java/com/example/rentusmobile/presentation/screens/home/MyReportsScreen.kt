package com.example.rentusmobile.presentation.screens.home

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

private data class ReportItem(val id: Int, val date: String, val type: String, val detail: String, val status: String)

@Composable
fun MyReportsScreen(
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
    onNavigateSettings: () -> Unit = {}
) {
    var showCreate by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val reports = remember {
        listOf(
            ReportItem(501, "2026-04-02", "Propiedad", "Publicación con datos incompletos", "pending"),
            ReportItem(502, "2026-04-01", "Usuario", "Comportamiento inapropiado en chat", "reviewed"),
            ReportItem(503, "2026-03-28", "Reseña", "Contenido ofensivo", "resolved")
        )
    }

    val filtered = reports.filter { it.detail.contains(query, ignoreCase = true) || it.type.contains(query, ignoreCase = true) }

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))) {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 84.dp).padding(horizontal = 16.dp, vertical = 18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("MIS REPORTES", color = Color(0xFFDA9C5F), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    AnimatedHeading("Reportes y Quejas", style = TextStyle(fontSize = 30.sp), gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 2800)
                    Text("Haz seguimiento al estado de tus reportes.", color = Color(0xFFD4C5B9), fontSize = 12.sp)
                }
                AppActionButton(text = "Nuevo", onClick = { showCreate = true }, modifier = Modifier.fillMaxWidth(0.34f), gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F)))
            }

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                placeholder = { Text("Buscar en mis reportes") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(filtered) { row ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFBF8))
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.BugReport, contentDescription = null, tint = Color(0xFFDA9C5F))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("#${row.id} · ${row.type}", fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50))
                                Text(row.detail, color = Color(0xFF6B7280), fontSize = 12.sp)
                                Text(row.date, color = Color(0xFF9CA3AF), fontSize = 11.sp)
                            }
                            StatusPill(row.status)
                        }
                    }
                }
            }
        }

        if (showCreate) {
            Dialog(onDismissRequest = { showCreate = false }) {
                Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        AnimatedHeading("Nuevo reporte", style = TextStyle(fontSize = 22.sp), gradientColors = listOf(Color(0xFF2E1D17), Color(0xFF8A5D34), Color(0xFFDA9C5F)), durationMillis = 2800)
                        OutlinedTextField(value = description, onValueChange = { description = it }, placeholder = { Text("Describe el reporte") }, modifier = Modifier.fillMaxWidth())
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            AppActionButton(text = "Enviar", onClick = { showCreate = false }, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFF2ECC71), Color(0xFF27AE60), Color(0xFF2ECC71)))
                            AppActionButton(text = "Cancelar", onClick = { showCreate = false }, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFFE74C3C), Color(0xFFC0392B), Color(0xFFE74C3C)))
                        }
                        TextButton(onClick = { showCreate = false }) { Text("Cerrar") }
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
            onNavigateMyRequests = onNavigateMyRequests,
            onNavigateRequests = onNavigateRequests,
            onNavigateMyReports = onNavigateMyReports,
            onNavigateSettings = onNavigateSettings
        )
    }
}

@Composable
private fun StatusPill(status: String) {
    val color = when (status) {
        "resolved" -> Color(0xFF2ECC71)
        "reviewed" -> Color(0xFF6366F1)
        "dismissed" -> Color(0xFF718096)
        else -> Color(0xFFF59E0B)
    }
    Text(
        text = status.uppercase(),
        color = color,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(color.copy(alpha = 0.15f), RoundedCornerShape(40.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}
