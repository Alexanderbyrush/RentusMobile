package com.example.rentusmobile.presentation.animation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset

object RentusAnimations {
    val EmphasizedEasing = CubicBezierEasing(0.2f, 0f, 0f, 1f)

    val EnterSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 420, easing = EmphasizedEasing)
    val ExitSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 320, easing = EmphasizedEasing)
    val PressSpring: FiniteAnimationSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    val SlideSpec: FiniteAnimationSpec<IntOffset> = tween(durationMillis = 460, easing = EmphasizedEasing)
}
