package com.example.rentusmobile.presentation.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatedBackground(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    blobColors: List<Color>,
    content: @Composable () -> Unit
) {
    val transition = rememberInfiniteTransition(label = "bg")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "phase"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawCircle(
                color = blobColors[0],
                radius = w * 0.34f,
                center = Offset(w * (0.24f + 0.06f * phase), h * (0.24f + 0.03f * phase))
            )
            drawCircle(
                color = blobColors[1],
                radius = w * 0.26f,
                center = Offset(w * (0.8f - 0.08f * phase), h * (0.62f - 0.04f * phase))
            )
            drawCircle(
                color = blobColors[2],
                radius = w * 0.18f,
                center = Offset(w * (0.52f + 0.04f * phase), h * (0.84f - 0.02f * phase))
            )
        }
        content()
    }
}
