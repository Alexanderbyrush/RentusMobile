package com.example.rentusmobile.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
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
    gradient: List<Color> = listOf(Color(0xFF3B251D), Color(0xFF5A3728), Color(0xFFDA9C5F)),
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale = if (pressed) 0.98f else 1f

    val transition = rememberInfiniteTransition(label = "appBtn")
    val shift by transition.animateFloat(
        initialValue = -220f,
        targetValue = 700f,
        animationSpec = infiniteRepeatable(tween(1700, easing = FastOutSlowInEasing), RepeatMode.Restart),
        label = "btnShift"
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
        Text(text, color = contentColor, fontWeight = FontWeight.Bold)
    }
}
