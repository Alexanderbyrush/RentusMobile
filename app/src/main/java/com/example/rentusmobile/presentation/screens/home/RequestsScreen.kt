package com.example.rentusmobile.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

private data class OwnerRequest(
    val id: Int,
    val propertyTitle: String,
    val address: String,
    val tenantName: String,
    val requestedDate: String,
    val requestedTime: String,
    val status: String
)

@Composable
fun RequestsScreen(
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
    var selected by remember { mutableStateOf<OwnerRequest?>(null) }
    val requests = remember {
        mutableStateOf(
            listOf(
                OwnerRequest(9001, "Penthouse Sky Lounge", "Bogotá, Chapinero", "Laura Mejía", "2026-04-10", "10:00", "pending"),
                OwnerRequest(9002, "Casa Forest Minimal", "Medellín, Laureles", "Andrés Ruiz", "2026-04-11", "15:30", "counter_proposed"),
                OwnerRequest(9003, "Loft Neon District", "Cali, Oeste", "Valentina Peña", "2026-04-13", "09:15", "accepted")
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))) {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 84.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.horizontalGradient(listOf(Color(0xFFDA9C5F), Color(0xFFB8791F))))
                    .padding(18.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) { Icon(Icons.Default.Home, contentDescription = null, tint = Color.White) }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        AnimatedHeading("Solicitudes (Dueño)", style = TextStyle(fontSize = 24.sp), gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 2800)
                        Text("Administra visitas recibidas para tus propiedades", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(requests.value) { req ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFBF8)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(modifier = Modifier.fillMaxWidth().height(130.dp).clip(RoundedCornerShape(12.dp))) {
                                Image(
                                    painter = painterResource(id = R.drawable.casa),
                                    contentDescription = req.propertyTitle,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            AnimatedHeading(req.propertyTitle, style = TextStyle(fontSize = 18.sp), gradientColors = listOf(Color(0xFF2E1D17), Color(0xFF8A5D34), Color(0xFFDA9C5F)), durationMillis = 2900)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(req.address, color = Color(0xFF5E5E5E), fontSize = 12.sp)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF7A5A45), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(req.tenantName, color = Color(0xFF374151), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Color(0xFF7A5A45), modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("${req.requestedDate} · ${req.requestedTime}", color = Color(0xFF6B7280), fontSize = 12.sp)
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                AppActionButton(text = "Revisar", onClick = { selected = req }, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFF3498DB), Color(0xFF2980B9), Color(0xFF3498DB)))
                                AppActionButton(text = "Rechazar", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFFE74C3C), Color(0xFFC0392B), Color(0xFFE74C3C)))
                            }
                        }
                    }
                }
            }
        }

        if (selected != null) {
            Dialog(onDismissRequest = { selected = null }) {
                Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column(modifier = Modifier.padding(18.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        AnimatedHeading("Revisar Solicitud", style = TextStyle(fontSize = 22.sp), gradientColors = listOf(Color(0xFF2E1D17), Color(0xFF8A5D34), Color(0xFFDA9C5F)), durationMillis = 2800)
                        Text("${selected!!.tenantName} quiere visitar ${selected!!.propertyTitle}", color = Color(0xFF4B5563))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            AppActionButton(text = "Aceptar", onClick = { selected = null }, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFF2ECC71), Color(0xFF27AE60), Color(0xFF2ECC71)))
                            AppActionButton(text = "Proponer fecha", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFFF39C12), Color(0xFFE67E22), Color(0xFFF39C12)))
                        }
                        AppActionButton(text = "Enviar contrato", onClick = { selected = null }, gradient = listOf(Color(0xFF27AE60), Color(0xFF229954), Color(0xFF27AE60)))
                        TextButton(onClick = { selected = null }) { Text("Cerrar") }
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
