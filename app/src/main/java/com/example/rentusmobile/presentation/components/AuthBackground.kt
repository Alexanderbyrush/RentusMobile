package com.example.rentusmobile.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rentusmobile.presentation.theme.Accent
import com.example.rentusmobile.presentation.theme.Background
import com.example.rentusmobile.presentation.theme.Primary

@Composable
fun AuthBackground(content: @Composable () -> Unit) {
    val floatTransition = rememberInfiniteTransition(label = "authBg")
    val driftA by floatTransition.animateFloat(
        initialValue = 0f,
        targetValue = 22f,
        animationSpec = infiniteRepeatable(
            animation = tween(5200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "driftA"
    )
    val driftB by floatTransition.animateFloat(
        initialValue = 0f,
        targetValue = -18f,
        animationSpec = infiniteRepeatable(
            animation = tween(6100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "driftB"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Background,
                        Accent,
                        Color(0xFFB8A890)
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .offset(x = (30 + driftA).dp, y = (140 + driftB).dp)
                .size(280.dp)
                .clip(CircleShape)
                .alpha(0.12f)
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .offset(x = (260 + driftB).dp, y = (520 + driftA).dp)
                .size(260.dp)
                .clip(CircleShape)
                .alpha(0.1f)
                .background(Primary)
        )
        content()
    }
}
