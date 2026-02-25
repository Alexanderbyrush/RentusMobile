package com.example.rentusmobile.presentation.screens.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Payments
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
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

data class PaymentRow(val id: Int, val amount: String, val status: String, val date: String, val type: String)

@Composable
fun PaymentsScreen(
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
    var query by remember { mutableStateOf("") }
    val rows = remember {
        listOf(
            PaymentRow(5531, "$2.500.000", "paid", "2026-01-10", "Arriendo"),
            PaymentRow(5532, "$800.000", "pending", "2026-01-20", "Depósito"),
            PaymentRow(5533, "$2.500.000", "failed", "2026-02-10", "Arriendo")
        )
    }
    val filtered = rows.filter { it.id.toString().contains(query) || it.type.contains(query, true) }

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))) {
        PaymentsAnimatedBg()
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 84.dp)) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color(0x22DA9C5F)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Payments, contentDescription = null, tint = Color(0xFFDA9C5F))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        AnimatedHeading("Pagos", style = androidx.compose.ui.text.TextStyle(fontSize = 28.sp), gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 2800)
                        Text("Historial y estado de tus pagos", color = Color(0xFFD4C5B9), fontSize = 12.sp)
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    AppActionButton(text = "Pagar ahora", onClick = {}, modifier = Modifier.weight(1f), contentColor = Color(0xFF1A0E0A), gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F)))
                    AppActionButton(text = "Métodos", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color(0xFF3B251D), Color(0xFF4D2F24), Color(0xFF6C4531)))
                }

                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text("Buscar por id o tipo") },
                    singleLine = true
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 4.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(filtered) { p ->
                    Card(shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color(0xF23A2318))) {
                        Row(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(Color(0x22DA9C5F)), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.CreditCard, contentDescription = null, tint = Color(0xFFDA9C5F))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Pago #${p.id}", color = Color.White, fontWeight = FontWeight.Bold)
                                Text("${p.date} · ${p.type}", color = Color(0xFFD4C5B9), fontSize = 12.sp)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(p.amount, color = Color(0xFFDA9C5F), fontWeight = FontWeight.ExtraBold)
                                val c = when (p.status) {
                                    "paid" -> Color(0xFF2ECC71)
                                    "pending" -> Color(0xFFF59E0B)
                                    else -> Color(0xFFE74C3C)
                                }
                                Text(p.status.uppercase(), color = c, fontSize = 11.sp, fontWeight = FontWeight.Bold)
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
private fun PaymentsAnimatedBg() {
    val t = rememberInfiniteTransition(label = "paybg")
    val shift by t.animateFloat(0f, -16f, infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Reverse), label = "s")
    Box(Modifier.fillMaxSize()) {
        repeat(12) { i ->
            Box(
                modifier = Modifier.padding(start = (i * 32).dp, top = (50 + i * 46).dp + shift.dp)
                    .size((4 + (i % 2)).dp).clip(CircleShape).background(Color(0x44DA9C5F))
            )
        }
    }
}
