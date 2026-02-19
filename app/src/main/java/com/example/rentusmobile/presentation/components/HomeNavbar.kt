package com.example.rentusmobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavbar(modifier: Modifier = Modifier) {
    var active by remember { mutableStateOf("Inicio") }
    var openMenu by remember { mutableStateOf(false) }

    val quickLinks = listOf(
        Triple("Inicio", Icons.Default.Home, "home"),
        Triple("Propiedades", Icons.Default.Apartment, "properties"),
        Triple("Nosotros", Icons.Default.Person, "about")
    )

    val menuItems = listOf(
        Pair("Mi Perfil", Icons.Default.Person),
        Pair("Notificaciones", Icons.Default.Notifications),
        Pair("Contratos", Icons.Default.Apartment),
        Pair("Pagos", Icons.Default.Payments),
        Pair("Mantenimiento", Icons.Default.Build),
        Pair("Mis Solicitudes", Icons.Default.Sms),
        Pair("Ajustes", Icons.Default.Settings)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(Brush.linearGradient(listOf(Color(0xFF3B251D), Color(0xFF2E1D17))))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Rent", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            Text("Us", color = Color(0xFFDA9C5F), fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            quickLinks.forEach { item ->
                val isActive = active == item.first
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(
                            if (isActive) Brush.linearGradient(listOf(Color(0xFFDA9C5F), Color(0xFFB8791F)))
                            else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent)),
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { active = item.first }
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    Icon(item.second, contentDescription = item.first, tint = Color.White, modifier = Modifier.size(18.dp))
                    Text(item.first, color = Color.White, fontSize = 11.sp)
                }
            }
        }

        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color.White.copy(alpha = 0.12f), CircleShape)
                .clickable { openMenu = true },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }
    }

    if (openMenu) {
        ModalBottomSheet(
            onDismissRequest = { openMenu = false },
            containerColor = Color(0xFFFAFAFA)
        ) {
            Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)) {
                Text("Opciones", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF2C3E50))
                Spacer(modifier = Modifier.height(8.dp))
                menuItems.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { openMenu = false }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0xFFF3E3D0), RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(item.second, contentDescription = item.first, tint = Color(0xFF3B251D))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(item.first, color = Color(0xFF2C3E50), fontSize = 15.sp)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
