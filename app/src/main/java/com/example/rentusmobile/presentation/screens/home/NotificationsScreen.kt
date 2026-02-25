package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Warning
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

data class NotificationItem(val title: String, val body: String, val time: String, val type: String, var read: Boolean)

@Composable
fun NotificationsScreen(
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
    var loading by remember { mutableStateOf(false) }
    val notifications = remember {
        mutableStateOf(
            listOf(
                NotificationItem("Nuevo contrato", "Tu contrato #212 está listo para revisión.", "Hace 5 min", "warning", false),
                NotificationItem("Pago recibido", "Recibiste un pago de arriendo exitosamente.", "Hace 30 min", "success", true),
                NotificationItem("Mantenimiento", "Se creó una solicitud para Apto 402.", "Hoy 09:20", "info", false)
            )
        )
    }
    val unreadCount = notifications.value.count { !it.read }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F7F5))) {
        NotificationsBackground()
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().background(Brush.horizontalGradient(listOf(Color(0xFFDA9C5F), Color(0xFFB8791F)))).padding(18.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.25f)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        AnimatedHeading("Notificaciones", style = androidx.compose.ui.text.TextStyle(fontSize = 22.sp), gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 2800)
                        Text("$unreadCount sin leer", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    }
                    if (unreadCount > 0) {
                        AppActionButton(
                            text = "Marcar todo",
                            onClick = { notifications.value = notifications.value.map { it.copy(read = true) } },
                            modifier = Modifier.fillMaxWidth(0.38f),
                            gradient = listOf(Color.White.copy(alpha = 0.95f), Color(0xFFF9E9D4), Color.White),
                            contentColor = Color(0xFF3B251D)
                        )
                    }
                }
            }

            if (loading) {
                Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(28.dp))
                    Text("Cargando notificaciones...", color = Color(0xFF6B7280))
                }
            } else if (notifications.value.isEmpty()) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0x66DA9C5F), modifier = Modifier.size(70.dp))
                    Text("No tienes notificaciones", color = Color(0xFF6B7280), fontWeight = FontWeight.SemiBold)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(notifications.value) { index, item ->
                        AnimatedVisibility(visible = true) {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = if (!item.read) Color(0xFFFFFAF3) else Color.White),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier.fillMaxWidth().clickable {
                                    val mutable = notifications.value.toMutableList()
                                    mutable[index] = mutable[index].copy(read = true)
                                    notifications.value = mutable
                                }
                            ) {
                                Row(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                    val bg = when (item.type) {
                                        "success" -> listOf(Color(0xFF27AE60), Color(0xFF229954))
                                        "warning" -> listOf(Color(0xFFF39C12), Color(0xFFE67E22))
                                        "info" -> listOf(Color(0xFF9B59B6), Color(0xFF8E44AD))
                                        else -> listOf(Color(0xFF3498DB), Color(0xFF2980B9))
                                    }
                                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(Brush.linearGradient(bg)), contentAlignment = Alignment.Center) {
                                        Icon(
                                            when (item.type) {
                                                "success" -> Icons.Default.Payments
                                                "warning" -> Icons.Default.Warning
                                                "info" -> Icons.Default.Apartment
                                                else -> Icons.Default.Notifications
                                            },
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.title, fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50))
                                        Text(item.body, color = Color(0xFF4B5563), fontSize = 13.sp)
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Schedule, contentDescription = null, tint = Color(0xFF9CA3AF), modifier = Modifier.size(12.dp))
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Text(item.time, color = Color(0xFF9CA3AF), fontSize = 11.sp)
                                        }
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFFB8791F))
                                        if (!item.read) Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFDA9C5F)))
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
            onNavigateMyReports = onNavigateMyReports,
            onNavigateSettings = onNavigateSettings
        )
    }
}

@Composable
private fun NotificationsBackground() {
    val transition = rememberInfiniteTransition(label = "notibg")
    val y by transition.animateFloat(0f, -20f, infiniteRepeatable(tween(1800, easing = LinearEasing), RepeatMode.Reverse), label = "y")
    Box(Modifier.fillMaxSize()) {
        repeat(8) { idx ->
            Box(
                modifier = Modifier.padding(start = (idx * 42).dp, top = (50 + idx * 60).dp + y.dp)
                    .size(4.dp).clip(CircleShape).background(Color(0x66DA9C5F))
            )
        }
    }
}
