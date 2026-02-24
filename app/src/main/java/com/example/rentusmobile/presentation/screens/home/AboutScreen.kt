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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.components.HomeNavbar

@Composable
fun AboutScreen(
    onNavigateHome: () -> Unit,
    onNavigateProperties: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 84.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text("Sobre Rentus", color = Color(0xFFC9915C), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Text("Una nueva forma\nde encontrar hogar.", color = Color(0xFFEDE8E1), fontSize = 40.sp, lineHeight = 42.sp)
            Text(
                "Nacimos en 2024 con una convicción: arrendar una propiedad debería ser claro, rápido y confiable.",
                color = Color(0xFFEDE8E1).copy(alpha = 0.75f),
                lineHeight = 24.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onNavigateProperties, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC9915C))) {
                    Text("Ver propiedades", color = Color(0xFF0E0D0B))
                }
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                    Text("Nuestra historia", color = Color(0xFFEDE8E1))
                }
            }

            StatsSection()
            StorySection()
            MvvSection()
            TeamSection()
            TestimonialsSection()
            AboutCta(onNavigateProperties)
            Spacer(modifier = Modifier.height(14.dp))
        }

        HomeNavbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedTab = "Nosotros",
            onNavigateHome = onNavigateHome,
            onNavigateProperties = onNavigateProperties,
            onNavigateAbout = {}
        )
    }
}

@Composable private fun StatsSection() {
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

@Composable private fun StorySection() {
    Text("Empezamos con un problema real.", color = Color(0xFFEDE8E1), fontSize = 28.sp, fontWeight = FontWeight.Medium)
    Text(
        "Encontrar arriendo en Colombia es complicado y opaco. Decidimos construir una plataforma directa y segura.",
        color = Color(0xFFEDE8E1).copy(alpha = 0.75f),
        lineHeight = 23.sp
    )
}

@Composable private fun MvvSection() {
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

@Composable private fun TeamSection() {
    Text("Cuatro personas. Una visión.", color = Color(0xFFEDE8E1), fontSize = 24.sp)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        listOf("AC", "MC", "CA", "JM").forEach {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Box(modifier = Modifier.size(50.dp).background(Color(0xFFC9915C).copy(alpha = 0.18f), CircleShape), contentAlignment = Alignment.Center) {
                    Text(it, color = Color(0xFFC9915C), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable private fun TestimonialsSection() {
    Text("Experiencias reales", color = Color(0xFFEDE8E1), fontSize = 24.sp)
    Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f))) {
        Column(Modifier.padding(14.dp)) {
            Text("★★★★★", color = Color(0xFFC9915C))
            Text("\"Encontré mi apartamento en menos de una semana.\"", color = Color(0xFFEDE8E1), fontStyle = FontStyle.Italic)
            Spacer(Modifier.height(8.dp))
            Text("María González", color = Color(0xFFEDE8E1).copy(alpha = 0.75f), fontSize = 12.sp)
        }
    }
}

@Composable private fun AboutCta(onNavigateProperties: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(18.dp))
            .padding(18.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Encuentra tu próximo hogar hoy mismo.", color = Color(0xFFEDE8E1), fontSize = 24.sp)
            Spacer(Modifier.height(10.dp))
            Button(onClick = onNavigateProperties, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC9915C))) {
                Text("Explorar propiedades", color = Color(0xFF0E0D0B))
            }
        }
    }
}
