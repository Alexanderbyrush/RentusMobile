package com.example.rentusmobile.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.rentusmobile.presentation.animation.AnimatedBackground
import com.example.rentusmobile.presentation.theme.Accent
import com.example.rentusmobile.presentation.theme.Background
import com.example.rentusmobile.presentation.theme.Primary

@Composable
fun AuthBackground(content: @Composable () -> Unit) {
    AnimatedBackground(
        colors = listOf(Background, Accent, Color(0xFFB8A890)),
        blobColors = listOf(
            Color.White.copy(alpha = 0.13f),
            Primary.copy(alpha = 0.12f),
            Color(0xFFDA9C5F).copy(alpha = 0.1f)
        )
    ) {
        content()
    }
}
