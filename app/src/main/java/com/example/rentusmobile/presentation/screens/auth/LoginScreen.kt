package com.example.rentusmobile.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onBackPressed: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Panel Izquierdo - 55%
            Box(
                modifier = Modifier
                    .weight(0.55f)
                    .fillMaxHeight()
                    .background(Color(0xFFF5F1E9)) // Color crema claro
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Logo y textos
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Logo
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier.padding(bottom = 20.dp)
                        ) {
                            Text(
                                text = "Rent",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                            Text(
                                text = "Us",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF8B6B4D) // Marrón claro
                            )
                        }

                        // Bienvenida
                        Text(
                            text = "Bienvenido de nuevo.",
                            fontSize = 20.sp,
                            color = Color(0xFF5C4033),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Texto descriptivo
                        Text(
                            text = "Retoma tu búsqueda donde la dejaste y sigue construyendo tu futuro hogar.",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }

                    // Imagen casa (opcional por ahora)
                    Image(
                        painter = painterResource(id = R.drawable.casa),
                        contentDescription = "Casa",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(200.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            // Panel Derecho - 45%
            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .fillMaxHeight()
                    .padding(horizontal = 40.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Título
                Text(
                    text = "Iniciar Sesión",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Botones de navegación (Iniciar Sesión | Registrarse | Volver)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(bottom = 24.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF8B6B4D) // Marrón
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Iniciar Sesión (activo)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(
                                    Color(0xFFD4C5B5), // Beige para el activo
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .clickable { }
                        ) {
                            Text(
                                text = "Iniciar Sesión",
                                color = Color(0xFF8B6B4D),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        // Registrarse
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { onNavigateToRegister() }
                        ) {
                            Text(
                                text = "Registrarse",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        // Volver
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { onBackPressed() }
                        ) {
                            Text(
                                text = "Volver",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                // Formulario
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.colors()
                    )

                    // Contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors()

                    )

                    // Botón Iniciar Sesión
                    Button(
                        onClick = { onNavigateToHome() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5C4033) // Marrón oscuro
                        )
                    ) {
                        Text(
                            text = "Iniciar Sesión",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                // Olvidaste contraseña
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 24.dp)
                        .clickable { }
                )

                // O continúa con
                Text(
                    text = "O continúa con",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Iconos redes sociales
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Apple
                    Icon(
                        painter = painterResource(id = R.drawable.ic_apple),
                        contentDescription = "Apple",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { }
                    )
                    // Facebook
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { }
                    )
                    // Google
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { }
                    )
                }
            }
        }
    }
}