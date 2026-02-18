package com.example.rentusmobile.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onBackPressed: () -> Unit
) {
    // Estados para los campos del formulario
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var idDocumento by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Panel Izquierdo (55%) - IGUAL QUE LOGIN
        Box(
            modifier = Modifier
                .weight(0.55f)
                .background(Color(0x99DED5C4))
                .graphicsLayer { alpha = 0.99f }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 100.dp)
                    .padding(vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Contenido superior (cambiado texto)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Logo
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.offset(x = (-8).dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logodark),
                            contentDescription = "Logo RentUs",
                            modifier = Modifier
                                .size(48.dp)
                                .offset(y = 6.dp)
                        )
                        Text(
                            text = "Rent",
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black
                        )
                        Text(
                            text = "Us",
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF4D2F24)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "¡Únete a Rent Us!",
                        fontSize = 20.sp,
                        color = Color(0xFF432D26),
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "Crea tu cuenta y empieza a construir el camino hacia tu próximo hogar.",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .widthIn(max = 400.dp),
                        lineHeight = 20.sp
                    )
                }

                // Imagen de casa (IGUAL)
                Image(
                    painter = painterResource(id = R.drawable.casa),
                    contentDescription = "Casa",
                    modifier = Modifier
                        .fillMaxWidth(1.1f)
                        .offset(x = (-160).dp)
                        .graphicsLayer {
                            scaleX = 1.5f
                            scaleY = 1.5f
                        }
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(
                                    brush = Brush.horizontalGradient(
                                        0f to Color.Transparent,
                                        0.85f to Color.Black
                                    ),
                                    blendMode = BlendMode.DstIn
                                )
                            }
                        },
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        // Panel Derecho (45%) - CON SCROLL POR LOS MUCHOS CAMPOS
        Column(
            modifier = Modifier
                .weight(0.45f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 48.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registrarse",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de navegación (con slider en Register)
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(48.dp)
                    .background(
                        color = Color(0xFF4D2F24),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(4.dp)
            ) {
                // Slider background - AHORA POSICIONADO EN REGISTER
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .offset(x = 110.dp) // (320/3 + 4) aprox
                            .background(
                                color = Color(0xFFDED5C4),
                                shape = RoundedCornerShape(50)
                            )
                    )
                }

                // Botones
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onNavigateToLogin() }
                    )
                    Text(
                        text = "Registrarse",
                        color = Color(0xFF4D2F24),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* Ya estamos en register */ }
                    )
                    Text(
                        text = "Volver",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onBackPressed() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Mensaje de error
            if (errorMessage != null) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8D7DA)
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = errorMessage!!,
                        color = Color(0xFF842029),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Formulario con 7 campos
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Nombre completo
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Teléfono
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                // Correo electrónico
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                // Dirección
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text("Dirección") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Documento
                OutlinedTextField(
                    value = idDocumento,
                    onValueChange = { idDocumento = it },
                    placeholder = { Text("Documento") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // Confirmar contraseña
                OutlinedTextField(
                    value = passwordConfirmation,
                    onValueChange = { passwordConfirmation = it },
                    placeholder = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // Botón Crear Cuenta
                Button(
                    onClick = {
                        // Validación básica (solo visual por ahora)
                        if (password == passwordConfirmation &&
                            name.isNotBlank() && email.isNotBlank()) {
                            errorMessage = null
                            onNavigateToHome()
                        } else {
                            errorMessage = "Verifica los datos e inténtalo de nuevo."
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF432D26)
                    )
                ) {
                    Text("Crear Cuenta", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "O continúa con",
                color = Color(0xFF666666),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Redes sociales
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = "Apple",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* TODO */ }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* TODO */ }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* TODO */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}