package com.example.rentusmobile.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AppActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
    gradient: List<Color> = listOf(Color(0xFF2A1B5F), Color(0xFF6B3FC9), Color(0xFF16B8C9), Color(0xFF9D7BFF)),
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    animationSeed: Int = text.hashCode()
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale = if (pressed) 0.98f else 1f

    val phaseOffset = remember(animationSeed) { kotlin.math.abs(animationSeed % 3000) }
    val transition = rememberInfiniteTransition(label = "appBtn")
    val shift by transition.animateFloat(
        initialValue = -260f,
        targetValue = 760f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(phaseOffset, StartOffsetType.FastForward)
        ),
        label = "btnShift"
    )

    val bubblePulse by transition.animateFloat(
        initialValue = 0.15f,
        targetValue = 0.45f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset((phaseOffset / 2), StartOffsetType.FastForward)
        ),
        label = "bubblePulse"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .scale(scale)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.linearGradient(
                    colors = gradient,
                    start = androidx.compose.ui.geometry.Offset(shift, 0f),
                    end = androidx.compose.ui.geometry.Offset(shift + 240f, 220f)
                )
            )
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val localShift = (shift % size.width)
            val dots = listOf(
                Triple(0.12f, 0.30f, 3.6f),
                Triple(0.27f, 0.68f, 2.8f),
                Triple(0.51f, 0.42f, 3.2f),
                Triple(0.74f, 0.62f, 2.4f),
                Triple(0.89f, 0.33f, 3.0f)
            )
            dots.forEachIndexed { index, (xSeed, ySeed, r) ->
                val x = ((size.width * xSeed) + localShift * (0.08f + index * 0.03f)) % size.width
                val y = size.height * ySeed
                drawCircle(
                    color = Color.White.copy(alpha = bubblePulse - (index * 0.04f)),
                    radius = r,
                    center = androidx.compose.ui.geometry.Offset(x, y)
                )
            }
        }
        Text(text, color = contentColor, fontWeight = FontWeight.Bold)
    }
}
