package com.example.rentusmobile.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AnimatedHeading(
    text: String,
    style: TextStyle,
    textAlign: TextAlign? = null
) {
    val transition = rememberInfiniteTransition(label = "heading")
    val shift by transition.animateFloat(
        initialValue = -180f,
        targetValue = 540f,
        animationSpec = infiniteRepeatable(animation = tween(2200), repeatMode = RepeatMode.Reverse),
        label = "headingShift"
    )

    Text(
        text = text,
        style = style.copy(
            fontWeight = FontWeight.ExtraBold,
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF2E1D17), Color(0xFF8A5D34), Color(0xFFDA9C5F)),
                start = androidx.compose.ui.geometry.Offset(shift, 0f),
                end = androidx.compose.ui.geometry.Offset(shift + 220f, 180f)
            )
        ),
        textAlign = textAlign
    )
}
