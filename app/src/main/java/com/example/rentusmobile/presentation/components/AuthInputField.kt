package com.example.rentusmobile.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.rentusmobile.presentation.theme.Border
import com.example.rentusmobile.presentation.theme.Error
import com.example.rentusmobile.presentation.theme.InputBg
import com.example.rentusmobile.presentation.theme.Primary
import com.example.rentusmobile.presentation.theme.TextSecondary
import com.example.rentusmobile.presentation.theme.White

@Composable
fun AuthInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTogglePasswordVisibility: () -> Unit = {},
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()
    val containerColor by animateColorAsState(
        targetValue = when {
            error != null -> Error.copy(alpha = 0.08f)
            focused -> White
            else -> InputBg
        },
        animationSpec = spring(),
        label = "inputContainer"
    )
    val borderColor by animateColorAsState(
        targetValue = when {
            error != null -> Error
            focused -> Primary
            else -> Border
        },
        animationSpec = spring(),
        label = "inputBorder"
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(containerColor, RoundedCornerShape(16.dp))
            .padding(vertical = 1.dp),
        interactionSource = interactionSource,
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        label = { Text(label, color = TextSecondary) },
        leadingIcon = { Icon(leadingIcon, contentDescription = label, tint = TextSecondary) },
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Toggle password",
                        tint = TextSecondary
                    )
                }
            }
        } else null,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(text = error, color = Error)
            }
        },
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = Error,
            cursorColor = Primary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )
}
