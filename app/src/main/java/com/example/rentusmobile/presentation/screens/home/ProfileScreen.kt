package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.res.painterResource
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    onNavigateHome: () -> Unit,
    onNavigateProperties: () -> Unit,
    onNavigateAbout: () -> Unit,
    onNavigateProfile: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
    onNavigateContracts: () -> Unit = {},
    onNavigatePayments: () -> Unit = {}
) {
    val stats = remember {
        listOf("12" to "Propiedades", "98%" to "Respuesta", "4.9" to "Rating")
    }

    val contactInfo = remember {
        listOf(
            Triple("Correo", "juan.rentus@email.com", Icons.Default.Mail),
            Triple("Teléfono", "+57 315 000 1111", Icons.Default.Phone),
            Triple("Ubicación", "Bogotá, Colombia", Icons.Default.LocationOn)
        )
    }

    val properties = remember {
        listOf(
            Triple("Apartamento Premium", "$2.500.000", "Disponible"),
            Triple("Casa Moderna Familiar", "$3.100.000", "Disponible"),
            Triple("Loft Ejecutivo", "$1.900.000", "Mantenimiento")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF0A0605), Color(0xFF1A0E0A), Color(0xFF2E1D17))))
    ) {
        ProfileAnimatedBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 84.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AvatarRings()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedHeading("Juan RentUs", style = TextStyle(fontSize = 30.sp, lineHeight = 32.sp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF3B82F6))
                    }
                    Text("@juan.rentus", color = Color(0xFFA0AEC0))

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0x22DA9C5F))
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Bogotá, Cundinamarca", color = Color(0xFFDA9C5F), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    }

                    Text(
                        "Asesor inmobiliario premium. Te ayudo a conseguir el hogar ideal con una experiencia clara, rápida y confiable.",
                        color = Color(0xFFE7DDD1),
                        fontSize = 14.sp
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        stats.forEach { (value, label) ->
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0x14FFFFFF))
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(value, color = Color(0xFFDA9C5F), fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                                    Text(label, color = Color(0xFFA0AEC0), fontSize = 11.sp)
                                }
                            }
                        }
                    }

                    AppActionButton(
                        text = "Editar perfil",
                        onClick = {},
                        gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F))
                    )
                }
            }

            contactInfo.forEach { (label, value, icon) ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0x12FFFFFF))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0x1FDA9C5F)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(icon, contentDescription = null, tint = Color(0xFFDA9C5F))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(label, color = Color(0xFFA0AEC0), fontSize = 11.sp)
                            Text(value, color = Color(0xFFF0E5DB), fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x12FFFFFF))
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Mis Propiedades", color = Color(0xFFF0E5DB), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    properties.forEachIndexed { index, (title, price, status) ->
                        val scale by animateFloatAsState(
                            targetValue = 1f,
                            animationSpec = tween(durationMillis = 450 + index * 120),
                            label = "propertyScale$index"
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .scale(scale),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF21150F))
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.casa),
                                    contentDescription = title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(140.dp)
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                                        Text(price, color = Color(0xFF2ECC71), fontWeight = FontWeight.ExtraBold)
                                    }
                                    Text(
                                        status,
                                        color = if (status == "Disponible") Color(0xFF2ECC71) else Color(0xFFF39C12),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(horizontal = 12.dp, vertical = 0.dp)) {
                                    ProfileChip(Icons.Default.Apartment, "95m²")
                                    ProfileChip(Icons.Default.Bed, "3")
                                    ProfileChip(Icons.Default.Bathtub, "2")
                                    ProfileChip(Icons.Default.AttachMoney, "Mes")
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf(Icons.Default.Home, Icons.Default.Star, Icons.Default.Phone).forEach { icon ->
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0x1FDA9C5F)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(icon, contentDescription = null, tint = Color(0xFFDA9C5F))
                    }
                }
            }
        }

        HomeNavbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(4f),
            selectedTab = "Perfil",
            onNavigateHome = onNavigateHome,
            onNavigateProperties = onNavigateProperties,
            onNavigateAbout = onNavigateAbout,
            onNavigateProfile = onNavigateProfile,
            onNavigateNotifications = onNavigateNotifications,
            onNavigateContracts = onNavigateContracts,
            onNavigatePayments = onNavigatePayments
        )
    }
}

@Composable
private fun ProfileAnimatedBackground() {
    val transition = rememberInfiniteTransition(label = "profilebg")
    val floatY by transition.animateFloat(
        initialValue = 0f,
        targetValue = -16f,
        animationSpec = infiniteRepeatable(tween(3600, easing = LinearEasing), repeatMode = RepeatMode.Reverse),
        label = "floatY"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        repeat(20) { index ->
            Box(
                modifier = Modifier
                    .padding(start = (index * 18).dp, top = (50 + index * 35).dp + floatY.dp)
                    .size((3 + index % 2).dp)
                    .clip(CircleShape)
                    .background(Color(0x99DA9C5F))
            )
        }
    }
}

@Composable
private fun AvatarRings() {
    val transition = rememberInfiniteTransition(label = "rings")
    val outerScale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
        label = "outer"
    )

    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .scale(outerScale)
                .clip(CircleShape)
                .background(Color(0x22DA9C5F))
        )
        Image(
            painter = painterResource(id = R.drawable.casa),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
private fun ProfileChip(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0x14FFFFFF))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(value, color = Color(0xFFCBD5E0), fontSize = 11.sp)
    }
}
