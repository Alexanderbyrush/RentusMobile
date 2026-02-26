package com.example.rentusmobile.presentation.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentusmobile.presentation.components.AuthBackground
import com.example.rentusmobile.presentation.components.AuthButton
import com.example.rentusmobile.presentation.components.AuthInputField
import com.example.rentusmobile.presentation.components.AuthModal
import com.example.rentusmobile.presentation.components.AuthTabRow
import com.example.rentusmobile.presentation.components.BrandLogo
import com.example.rentusmobile.presentation.components.DividerWithText
import com.example.rentusmobile.presentation.components.SocialButton
import com.example.rentusmobile.presentation.theme.Primary
import com.example.rentusmobile.presentation.theme.TextPrimary
import com.example.rentusmobile.presentation.theme.TextSecondary
import com.example.rentusmobile.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateBack: () -> Unit,
    onLoginSuccess: () -> Unit,
    onForgotPassword: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.state

    AuthBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            BrandLogo(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 28.dp, top = 28.dp)
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AuthModal(
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = onNavigateBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = TextSecondary)
                            }
                            Text("Volver", color = TextSecondary)
                        }

                        AuthTabRow(
                            isLoginSelected = true,
                            onLoginClick = {},
                            onRegisterClick = onNavigateToRegister
                        )

                        Text("Accede a tu cuenta", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text("Ingresa tus credenciales para continuar", color = TextSecondary)

                        AuthInputField(
                            value = state.email,
                            onValueChange = viewModel::onEmailChange,
                            label = "Email",
                            leadingIcon = Icons.Default.Email,
                            error = state.validationErrors["email"]
                        )

                        AuthInputField(
                            value = state.password,
                            onValueChange = viewModel::onPasswordChange,
                            label = "Contraseña",
                            leadingIcon = Icons.Default.Lock,
                            isPassword = true,
                            isPasswordVisible = state.isPasswordVisible,
                            onTogglePasswordVisibility = viewModel::onTogglePasswordVisibility,
                            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            error = state.validationErrors["password"]
                        )

                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Checkbox(checked = state.rememberMe, onCheckedChange = viewModel::onRememberMeChange, modifier = Modifier.scale(0.82f))
                                Text("Recordarme", color = TextPrimary)
                            }
                            Text(
                                text = "¿Olvidaste tu contraseña?",
                                color = Primary,
                                fontSize = 12.sp,
                                maxLines = 1,
                                modifier = Modifier.clickable {
                                    viewModel.onForgotPasswordClick()
                                    onForgotPassword()
                                }
                            )
                        }

                        state.errorMessage?.let { Text(it, color = com.example.rentusmobile.presentation.theme.Error) }

                        AuthButton(
                            text = "Iniciar Sesión",
                            enabled = true,
                            isLoading = state.isLoading,
                            onClick = { viewModel.onLoginClick(onLoginSuccess) }
                        )

                        DividerWithText("O continúa con")

                        SocialButton(
                            text = "Continuar con Google",
                            onClick = viewModel::onGoogleLoginClick
                        )

                        val footer = buildAnnotatedString {
                            append("¿No tienes una cuenta? ")
                            addStyle(SpanStyle(color = TextSecondary), 0, length)
                            withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.Medium)) {
                                append("Regístrate gratis")
                            }
                        }
                        ClickableText(text = footer, onClick = { onNavigateToRegister() })
                    }
                }
            }
        }
    }
}
