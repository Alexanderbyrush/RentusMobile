package com.example.rentusmobile.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentusmobile.presentation.theme.Primary
import com.example.rentusmobile.presentation.theme.TextSecondary
import com.example.rentusmobile.presentation.theme.White

@Composable
fun AuthTabRow(
    isLoginSelected: Boolean,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val indicatorOffset = animateDpAsState(if (isLoginSelected) 0.dp else 140.dp, label = "tabOffset")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(TextSecondary.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
                .padding(start = indicatorOffset.value)
                .shadow(8.dp, RoundedCornerShape(12.dp))
                .background(White, RoundedCornerShape(12.dp))
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            TabItem("Iniciar Sesión", isLoginSelected, onLoginClick, Modifier.weight(1f))
            TabItem("Registrarse", !isLoginSelected, onRegisterClick, Modifier.weight(1f))
        }
    }
}

@Composable
private fun TabItem(text: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(40.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Primary else TextSecondary,
            fontWeight = FontWeight.SemiBold
        )
    }
}
