package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.animation.AnimatedBackground
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar
import kotlinx.coroutines.delay

@Composable
fun AboutScreen(
    onNavigateHome: () -> Unit,
    onNavigateProperties: () -> Unit,
    onNavigateProfile: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
    onNavigateContracts: () -> Unit = {},
    onNavigatePayments: () -> Unit = {}
) {
    AnimatedBackground(
        colors = listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416)),
        blobColors = listOf(
            Color(0xFFC9915C).copy(alpha = 0.12f),
            Color.White.copy(alpha = 0.06f),
            Color(0xFF8B5E34).copy(alpha = 0.14f)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 84.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                AboutSection(0) {
                    Text("Sobre Rentus", color = Color(0xFFC9915C), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    AnimatedHeading(
                        "Una nueva forma\nde encontrar hogar.",
                        style = TextStyle(fontSize = 40.sp, lineHeight = 42.sp)
                    )
                    Text(
                        "Nacimos en 2024 con una convicción: arrendar una propiedad debería ser claro, rápido y confiable.",
                        color = Color(0xFFEDE8E1).copy(alpha = 0.75f),
                        lineHeight = 24.sp
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        AppActionButton(
                            text = "Ver propiedades",
                            onClick = onNavigateProperties,
                            modifier = Modifier.weight(1f),
                            contentColor = Color(0xFF0E0D0B),
                            gradient = listOf(Color(0xFFC9915C), Color(0xFFE0B686), Color(0xFFC9915C))
                        )
                        AppActionButton(
                            text = "Nuestra historia",
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            gradient = listOf(Color(0xFF3B251D), Color(0xFF5D3B2E), Color(0xFF7F5944))
                        )
                    }
                }

                AboutSection(1) { StatsSection() }
                AboutSection(2) { StorySection() }
                AboutSection(3) { MvvSection() }
                AboutSection(4) { TeamSection() }
                AboutSection(5) { TestimonialsSection() }
                AboutSection(6) { AboutCta(onNavigateProperties) }
                Spacer(modifier = Modifier.height(14.dp))
            }

            HomeNavbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedTab = "Nosotros",
                onNavigateHome = onNavigateHome,
                onNavigateProperties = onNavigateProperties,
                onNavigateAbout = {},
                onNavigateProfile = onNavigateProfile,
                onNavigateNotifications = onNavigateNotifications,
                onNavigateContracts = onNavigateContracts,
                onNavigatePayments = onNavigatePayments
            )
        }
    }
}

@Composable
private fun AboutSection(index: Int, content: @Composable ColumnScope.() -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(110L * index)
        visible = true
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 5 })
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), content = content)
    }
}

@Composable
private fun StatsSection() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf("2024" to "Año", "500+" to "Propiedades", "1K+" to "Usuarios").forEach {
            Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))) {
                Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(it.first, color = Color(0xFFC9915C), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text(it.second, color = Color(0xFFEDE8E1).copy(alpha = 0.65f), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun StorySection() {
    AnimatedHeading("Empezamos con un problema real.", style = TextStyle(fontSize = 28.sp))
    Text(
        "Encontrar arriendo en Colombia es complicado y opaco. Decidimos construir una plataforma directa y segura.",
        color = Color(0xFFEDE8E1).copy(alpha = 0.75f),
        lineHeight = 23.sp
    )
}

@Composable
private fun MvvSection() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        listOf("Misión" to "Simplificar el acceso a propiedades", "Visión" to "Ser referencia en Colombia", "Valores" to "Transparencia y honestidad")
            .forEach {
                Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f))) {
                    Column(Modifier.padding(14.dp)) {
                        Text(it.first, color = Color(0xFFEDE8E1), fontWeight = FontWeight.Bold)
                        Text(it.second, color = Color(0xFFEDE8E1).copy(alpha = 0.68f), fontSize = 13.sp)
                    }
                }
            }
    }
}

@Composable
private fun TeamSection() {
    AnimatedHeading("Cuatro personas. Una visión.", style = TextStyle(fontSize = 24.sp))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        listOf("AC", "MC", "CA", "JM").forEach {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.size(50.dp).background(Color(0xFFC9915C).copy(alpha = 0.18f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(it, color = Color(0xFFC9915C), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun TestimonialsSection() {
    AnimatedHeading("Experiencias reales", style = TextStyle(fontSize = 24.sp))
    Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f))) {
        Column(Modifier.padding(14.dp)) {
            Text("★★★★★", color = Color(0xFFC9915C))
            Text("\"Encontré mi apartamento en menos de una semana.\"", color = Color(0xFFEDE8E1), fontStyle = FontStyle.Italic)
            Spacer(Modifier.height(8.dp))
            Text("María González", color = Color(0xFFEDE8E1).copy(alpha = 0.75f), fontSize = 12.sp)
        }
    }
}

@Composable
private fun AboutCta(onNavigateProperties: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(listOf(Color.White.copy(alpha = 0.04f), Color(0xFFC9915C).copy(alpha = 0.08f))),
                RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedHeading(
                "Encuentra tu próximo hogar hoy mismo.",
                style = TextStyle(fontSize = 24.sp),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(10.dp))
            AppActionButton(
                text = "Explorar propiedades",
                onClick = onNavigateProperties,
                contentColor = Color(0xFF0E0D0B),
                gradient = listOf(Color(0xFFC9915C), Color(0xFFE0B686), Color(0xFFC9915C))
            )
        }
    }
}
