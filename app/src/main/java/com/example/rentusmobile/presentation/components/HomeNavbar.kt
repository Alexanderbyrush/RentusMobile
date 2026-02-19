package com.example.rentusmobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun HomeNavbar(modifier: Modifier = Modifier) {
    var active by remember { mutableStateOf("Inicio") }
    val links = listOf("Inicio", "Propiedades", "Nosotros")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Brush.linearGradient(listOf(Color(0xFF3B251D), Color(0xFF2E1D17))))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Rent", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            Text("Us", color = Color(0xFFDA9C5F), fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        }

        Row(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            links.forEach { item ->
                Box(
                    modifier = Modifier
                        .background(
                            if (active == item) Brush.linearGradient(listOf(Color(0xFFDA9C5F), Color(0xFFB8791F))) else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent)),
                            RoundedCornerShape(7.dp)
                        )
                        .clickable { active = item }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(item, color = Color.White, fontSize = 13.sp)
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(50))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text("EN | ES", color = Color.White, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .background(Brush.linearGradient(listOf(Color(0xFFDA9C5F), Color(0xFFB8791F))), RoundedCornerShape(7.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text("Login", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }
        }
    }
}
