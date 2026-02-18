package com.example.rentusmobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AuthBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF2563EB), Color(0xFF1E40AF))))
    ) {
        Particle(42.dp, 70.dp, 90.dp, 0.24f)
        Particle(300.dp, 140.dp, 48.dp, 0.16f)
        Particle(220.dp, 520.dp, 72.dp, 0.20f)
        Particle(20.dp, 650.dp, 54.dp, 0.14f)
        content()
    }
}

@Composable
private fun Particle(x: androidx.compose.ui.unit.Dp, y: androidx.compose.ui.unit.Dp, size: androidx.compose.ui.unit.Dp, alpha: Float) {
    Box(
        modifier = Modifier
            .offset(x = x, y = y)
            .size(size)
            .clip(CircleShape)
            .alpha(alpha)
            .background(Color.White)
    )
}
