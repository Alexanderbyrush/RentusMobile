package com.example.rentusmobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.theme.DotActive
import com.example.rentusmobile.presentation.theme.DotInactive
import com.example.rentusmobile.presentation.theme.TextPrimary
import com.example.rentusmobile.presentation.theme.TextSecondary

private data class MessageItem(val title: String, val description: String)

@Composable
fun DynamicMessageSection(modifier: Modifier = Modifier) {
    val messages = listOf(
        MessageItem(
            title = "Búsqueda inteligente",
            description = "Usa nuestros filtros avanzados para encontrar exactamente lo que necesitas en segundos"
        ),
        MessageItem(
            title = "Encuentra tu hogar ideal",
            description = "Accede a miles de propiedades disponibles en tu ciudad y comienza tu búsqueda hoy mismo"
        )
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = messages[selectedIndex].title,
            fontSize = 42.sp,
            lineHeight = 46.sp,
            color = TextPrimary,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = messages[selectedIndex].description,
            fontSize = 18.sp,
            lineHeight = 28.sp,
            color = TextSecondary
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            messages.indices.forEach { index ->
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(if (index == selectedIndex) DotActive else DotInactive, CircleShape)
                        .clickable { selectedIndex = index }
                )
            }
        }
    }
}
