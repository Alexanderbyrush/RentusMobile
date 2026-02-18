package com.example.rentusmobile.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.AuthBackground
import com.example.rentusmobile.presentation.components.AuthButton
import com.example.rentusmobile.presentation.components.AuthInputField
import com.example.rentusmobile.presentation.components.AuthModal
import com.example.rentusmobile.presentation.components.AuthTabRow
import com.example.rentusmobile.presentation.components.DividerWithText
import com.example.rentusmobile.presentation.components.SocialButton
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logodark),
                contentDescription = "RentUs",
                modifier = Modifier.size(120.dp)
            )

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AuthModal(modifier = Modifier.fillMaxWidth(0.92f)) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = onNavigateBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                            }
                            Text("Volver", color = Color(0xFF6B7280))
                        }

                        AuthTabRow(
                            isLoginSelected = true,
                            onLoginClick = {},
                            onRegisterClick = onNavigateToRegister
                        )

                        Text("Accede a tu cuenta", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1F2937))
                        Text("Ingresa tus credenciales para continuar", color = Color(0xFF6B7280))

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
                            visualTransformation = if (state.isPasswordVisible) androidx.compose.ui.text.input.VisualTransformation.None else PasswordVisualTransformation(),
                            error = state.validationErrors["password"]
                        )

                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Checkbox(checked = state.rememberMe, onCheckedChange = viewModel::onRememberMeChange)
                                Text("Recordarme", color = Color(0xFF1F2937))
                            }
                            Text(
                                text = "¿Olvidaste tu contraseña?",
                                color = Color(0xFF2563EB),
                                modifier = Modifier.clickable {
                                    viewModel.onForgotPasswordClick()
                                    onForgotPassword()
                                }
                            )
                        }

                        state.errorMessage?.let { Text(it, color = Color(0xFFEF4444)) }

                        AuthButton(
                            text = "Iniciar Sesión",
                            enabled = state.isFormValid,
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
                            withStyle(style = SpanStyle(color = Color(0xFF2563EB), fontWeight = FontWeight.Medium)) {
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
