package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

data class VisitRequestItem(
    val id: Int,
    val property: String,
    val address: String,
    val date: String,
    val time: String,
    val status: String
)

@Composable
fun MyRequestsScreen(
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
    onNavigateMyReports: () -> Unit = {}
) {
    var loading by remember { mutableStateOf(false) }
    val requests = remember {
        mutableStateOf(
            listOf(
                VisitRequestItem(3001, "Apartamento Premium", "Bogotá, Chapinero", "2026-03-20", "10:00", "pending"),
                VisitRequestItem(3002, "Casa Moderna Familiar", "Medellín, Laureles", "2026-03-22", "14:30", "counter_proposed"),
                VisitRequestItem(3003, "Loft Ejecutivo", "Cali, Oeste", "2026-03-18", "09:15", "accepted")
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F7F5))) {
        RequestsParticles()

        Column(modifier = Modifier.fillMaxSize().padding(bottom = 84.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.horizontalGradient(listOf(Color(0xFFDA9C5F), Color(0xFFB8791F))))
                    .padding(18.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        AnimatedHeading("Mis Solicitudes", style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp), gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 2800)
                        Text("Seguimiento de tus solicitudes como inquilino", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    }
                }
            }

            if (loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Cargando solicitudes...", color = Color(0xFF6B7280))
                }
            } else if (requests.value.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Color(0x66DA9C5F), modifier = Modifier.size(80.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("No tienes solicitudes aún", color = Color(0xFF4B5563), fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(14.dp))
                    AppActionButton(text = "Buscar propiedades", onClick = onNavigateProperties)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(requests.value) { _, req ->
                        AnimatedVisibility(visible = true) {
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Home, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(req.property, fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50), modifier = Modifier.weight(1f))
                                        StatusChip(req.status)
                                    }
                                    Text(req.address, color = Color(0xFF6B7280), fontSize = 13.sp)
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Schedule, contentDescription = null, tint = Color(0xFF9CA3AF), modifier = Modifier.size(13.dp))
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text("${req.date} · ${req.time}", color = Color(0xFF4B5563), fontSize = 12.sp)
                                    }

                                    when (req.status) {
                                        "counter_proposed" -> {
                                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                AppActionButton(text = "Aceptar", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFF2ECC71), Color(0xFF27AE60), Color(0xFF2ECC71)))
                                                AppActionButton(text = "Rechazar", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFFE74C3C), Color(0xFFC0392B), Color(0xFFE74C3C)))
                                            }
                                        }
                                        "pending" -> {
                                            AppActionButton(text = "Cancelar solicitud", onClick = {}, gradient = listOf(Color(0xFFE74C3C), Color(0xFFC0392B), Color(0xFFE74C3C)))
                                        }
                                        else -> {
                                            AppActionButton(text = "Ver detalle", onClick = {}, gradient = listOf(Color(0xFF3498DB), Color(0xFF2980B9), Color(0xFF3498DB)))
                                        }
                                    }
                                }
                            }
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
            onNavigateMyRequests = onNavigateMyRequests,
            onNavigateRequests = onNavigateRequests,
            onNavigateMyReports = onNavigateMyReports
        )
    }
}

@Composable
private fun StatusChip(status: String) {
    val (bg, fg, icon) = when (status) {
        "accepted" -> Triple(Color(0xFFEAF8EE), Color(0xFF2ECC71), Icons.Default.CheckCircle)
        "rejected" -> Triple(Color(0xFFFDECEC), Color(0xFFE74C3C), Icons.Default.Close)
        "counter_proposed" -> Triple(Color(0xFFFFF7E8), Color(0xFFF39C12), Icons.Default.Schedule)
        else -> Triple(Color(0xFFFFF7E8), Color(0xFFF59E0B), Icons.Default.Schedule)
    }
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = fg, modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(status.uppercase(), color = fg, fontWeight = FontWeight.Bold, fontSize = 10.sp)
    }
}

@Composable
private fun RequestsParticles() {
    val transition = rememberInfiniteTransition(label = "req-bg")
    val yShift by transition.animateFloat(
        initialValue = 0f,
        targetValue = -16f,
        animationSpec = infiniteRepeatable(tween(1900, easing = LinearEasing), RepeatMode.Reverse),
        label = "req-shift"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        repeat(6) { i ->
            Box(
                modifier = Modifier
                    .padding(start = (50 + i * 46).dp, top = (80 + i * 60).dp + yShift.dp)
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color(0x66DA9C5F))
            )
        }
    }
}
