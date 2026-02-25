package com.example.rentusmobile.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentusmobile.presentation.animation.RentusAnimations
import com.example.rentusmobile.presentation.theme.Primary
import com.example.rentusmobile.presentation.theme.PrimaryDark
import com.example.rentusmobile.presentation.theme.White

@Composable
fun AuthButton(
    text: String,
    enabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = MutableInteractionSource()
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = RentusAnimations.PressSpring,
        label = "authBtnScale"
    )

    val transition = rememberInfiniteTransition(label = "buttonGlow")
    val glowShift by transition.animateFloat(
        initialValue = -280f,
        targetValue = 900f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "glowShift"
    )

    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        interactionSource = interactionSource,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .scale(scale),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = PrimaryDark.copy(alpha = 0.4f),
            contentColor = White,
            disabledContentColor = White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(PrimaryDark, Primary, Color(0xFF7D512E), PrimaryDark),
                        start = androidx.compose.ui.geometry.Offset(glowShift, 0f),
                        end = androidx.compose.ui.geometry.Offset(glowShift + 260f, 220f)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(targetState = isLoading, label = "buttonContent") { loading ->
                if (loading) {
                    CircularProgressIndicator(modifier = Modifier.size(18.dp), color = White, strokeWidth = 2.dp)
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = text, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 6.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    }
}
