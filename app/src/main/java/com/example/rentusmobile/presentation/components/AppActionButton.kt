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
            val bubbleSeeds = listOf(
                Triple(0.04f, 0.12f, 2.0f), Triple(0.11f, 0.24f, 2.8f), Triple(0.18f, 0.38f, 1.9f), Triple(0.26f, 0.52f, 3.2f),
                Triple(0.33f, 0.68f, 2.4f), Triple(0.40f, 0.82f, 1.8f), Triple(0.47f, 0.18f, 2.9f), Triple(0.55f, 0.34f, 2.1f),
                Triple(0.62f, 0.49f, 3.0f), Triple(0.69f, 0.66f, 1.9f), Triple(0.76f, 0.80f, 2.5f), Triple(0.83f, 0.27f, 2.7f),
                Triple(0.90f, 0.43f, 2.2f), Triple(0.97f, 0.58f, 1.7f), Triple(0.15f, 0.74f, 2.3f), Triple(0.29f, 0.09f, 2.6f),
                Triple(0.44f, 0.57f, 2.0f), Triple(0.58f, 0.72f, 2.8f), Triple(0.72f, 0.14f, 2.1f), Triple(0.86f, 0.91f, 1.8f)
            )
            bubbleSeeds.forEachIndexed { index, (xSeed, ySeed, r) ->
                val driftFactor = 0.04f + (index % 6) * 0.03f
                val reverse = if (index % 2 == 0) 1f else -1f
                val x = ((size.width * xSeed) + localShift * driftFactor * reverse + size.width) % size.width
                val yNudge = ((localShift / size.width) - 0.5f) * 8f * reverse
                val y = (size.height * ySeed + yNudge).coerceIn(0f, size.height)
                drawCircle(
                    color = Color.White.copy(alpha = (bubblePulse - (index % 5) * 0.05f).coerceAtLeast(0.08f)),
                    radius = r,
                    center = androidx.compose.ui.geometry.Offset(x, y)
                )
            }
        }
        Text(text, color = contentColor, fontWeight = FontWeight.Bold)
    }
}
