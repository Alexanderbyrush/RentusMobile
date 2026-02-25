package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

private data class DemoProperty(
    val title: String,
    val city: String,
    val price: String,
    val area: String,
    val bedrooms: String,
    val bathrooms: String,
    val status: String
)

@Composable
fun HomeScreen(
    onNavigateHome: () -> Unit = {},
    onNavigateProperties: () -> Unit = {},
    onNavigateAbout: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
    onNavigateContracts: () -> Unit = {},
    onNavigatePayments: () -> Unit = {},
    onNavigateMaintenance: () -> Unit = {},
    onNavigateMyRequests: () -> Unit = {},
    onNavigateRequests: () -> Unit = {},
    onNavigateMyReports: () -> Unit = {},
    onNavigateSettings: () -> Unit = {}
) {
    val properties = remember {
        listOf(
            DemoProperty("Penthouse Sky Lounge", "Bogotá", "$6.200.000", "220m²", "4", "4", "Disponible"),
            DemoProperty("Casa Forest Minimal", "Medellín", "$4.700.000", "260m²", "4", "4", "Top"),
            DemoProperty("Loft Neon District", "Cali", "$3.100.000", "92m²", "2", "2", "Nuevo")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF0D0A09), Color(0xFF241711), Color(0xFF3B251D))))
    ) {
        CinematicParticlesBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 84.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection()
            SearchSection()
            PropertiesSection(properties)
            CtaSection(onNavigateProperties)
            Spacer(modifier = Modifier.height(24.dp))
        }

        HomeNavbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedTab = "Inicio",
            onNavigateHome = onNavigateHome,
            onNavigateProperties = onNavigateProperties,
            onNavigateAbout = onNavigateAbout,
            onNavigateProfile = onNavigateProfile,
            onNavigateNotifications = onNavigateNotifications,
            onNavigateContracts = onNavigateContracts,
            onNavigatePayments = onNavigatePayments,
            onNavigateMaintenance = onNavigateMaintenance,
            onNavigateMyRequests = onNavigateMyRequests,
            onNavigateRequests = onNavigateRequests,
            onNavigateMyReports = onNavigateMyReports,
            onNavigateSettings = onNavigateSettings
        )
    }
}

@Composable
private fun CinematicParticlesBackground() {
    val transition = rememberInfiniteTransition(label = "cinematicParticles")
    val yA by transition.animateFloat(
        initialValue = 0f,
        targetValue = -280f,
        animationSpec = infiniteRepeatable(tween(12000, easing = LinearEasing), RepeatMode.Restart),
        label = "yA"
    )
    val yB by transition.animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(tween(15000, easing = LinearEasing), RepeatMode.Restart),
        label = "yB"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        repeat(84) { index ->
            val x = (index * 53f) % w
            val yBase = ((index * 97f) % h) + 120f
            val y = (yBase + yA).let { if (it < -40f) it + h + 200f else it }
            drawCircle(
                color = if (index % 3 == 0) Color(0x66DA9C5F) else Color(0x33F6D2A5),
                radius = (2f + (index % 4)),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
        }

        repeat(56) { index ->
            val x = (index * 71f + 32f) % w
            val yBase = ((index * 113f) % h) + 180f
            val y = (yBase + yB).let { if (it < -40f) it + h + 240f else it }
            drawCircle(
                color = Color.White.copy(alpha = 0.18f),
                radius = (1.4f + (index % 3)),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
        }
    }
}


@Composable
private fun HeroSection() {
    GlowingSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp), corner = 24.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(Color(0xFF2E1D17), Color(0xFF3B251D), Color(0xFF4D2F24))))
                .padding(horizontal = 22.dp, vertical = 24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color.White.copy(alpha = 0.12f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Modo Cinemático 2026", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }

                AnimatedHeading(
                    text = "El hogar que sueñas\nse ve así de brutal",
                    style = TextStyle(fontSize = 34.sp, lineHeight = 36.sp),
                    gradientColors = listOf(Color(0xFFFFE7C7), Color(0xFFF6D2A5), Color(0xFFDA9C5F), Color(0xFFB77A49)),
                    durationMillis = 2900
                )

                Text(
                    text = "Experiencia inmersiva con cards iluminadas, navegación premium y propiedades de otro nivel.",
                    color = Color(0xFFEFE8DD).copy(alpha = 0.92f),
                    fontSize = 14.sp
                )

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatChip("1,200+", "Propiedades", Icons.Default.Home)
                    StatChip("980+", "Clientes", Icons.Default.CheckCircle)
                    StatChip("5⭐", "Rating", Icons.Default.Star)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.casa),
                        contentDescription = "Showcase",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xB0000000)))))
                    Column(modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)) {
                        Text("Tour en vivo", color = Color(0xFFF6D2A5), fontWeight = FontWeight.Bold)
                        Text("Explorar en mapa", color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text("En vivo", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun StatChip(number: String, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(color = Color.White.copy(alpha = 0.1f), shape = RoundedCornerShape(14.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(number, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Text(label, color = Color.White.copy(alpha = 0.85f), fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun SearchSection() {
    GlowingSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 2.dp), corner = 18.dp) {
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF17110E)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Buscar propiedades", fontWeight = FontWeight.Bold, color = Color(0xFFF0E5DB))
                SearchInput("Ciudad", Modifier.fillMaxWidth())
                SearchInput("Tipo", Modifier.fillMaxWidth())
                AppActionButton(
                    text = "Buscar",
                    onClick = {},
                    gradient = listOf(Color(0xFF3B251D), Color(0xFF2E1D17), Color(0xFFDA9C5F)),
                    animationSeed = 101
                )
            }
        }
    }
}

@Composable
private fun SearchInput(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF241711))
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text(label, color = Color(0xFFBFAF9F), fontSize = 13.sp)
    }
}

@Composable
private fun PropertiesSection(properties: List<DemoProperty>) {
    Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        AnimatedHeading(
            text = "Propiedades destacadas",
            style = TextStyle(fontSize = 26.sp),
            gradientColors = listOf(Color(0xFFFFE7C7), Color(0xFFF6D2A5), Color(0xFFDA9C5F)),
            durationMillis = 2800
        )
        Text("Cartas con borde iluminado y volumen para una experiencia premium.", color = Color(0xFFE8DAC8))

        properties.forEach { property ->
            GlowingSurface(modifier = Modifier.fillMaxWidth(), corner = 18.dp) {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1B130F)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.casa),
                                contentDescription = property.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0x77000000)))))
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFF27AE60))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(property.status, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.width(220.dp)) {
                                    AnimatedHeading(
                                        text = property.title,
                                        style = TextStyle(fontSize = 18.sp),
                                        gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)),
                                        durationMillis = 3000
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(14.dp))
                                        Text(property.city, color = Color(0xFFBCA99A), fontSize = 12.sp)
                                    }
                                }
                                Text(property.price, fontWeight = FontWeight.ExtraBold, color = Color(0xFF2ECC71), fontSize = 18.sp)
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                FeatureMini(Icons.Default.Apartment, property.area)
                                FeatureMini(Icons.Default.Bed, property.bedrooms)
                                FeatureMini(Icons.Default.Bathtub, property.bathrooms)
                            }

                            AppActionButton(
                                text = "Ver detalles",
                                onClick = {},
                                gradient = listOf(Color(0xFF4D2F24), Color(0xFF5D3A2D), Color(0xFFDA9C5F)),
                                animationSeed = property.title.hashCode()
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
private fun FeatureMini(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF2A1C16))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(13.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(value, fontSize = 12.sp, color = Color(0xFFE7D8C8), fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun CtaSection(onNavigateProperties: () -> Unit) {
    GlowingSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 22.dp), corner = 22.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(Color(0xFF2E1D17), Color(0xFF3B251D))))
                .padding(22.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.height(8.dp))
                AnimatedHeading(
                    "¿Listo para encontrar tu próximo hogar?",
                    style = TextStyle(fontSize = 22.sp),
                    gradientColors = listOf(Color(0xFFFFEED7), Color(0xFFF6D2A5), Color(0xFFC8A97E)),
                    durationMillis = 2800
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Explora todas las propiedades o comunícate con nuestro equipo.", color = Color.White.copy(alpha = 0.9f))
                Spacer(modifier = Modifier.height(14.dp))
                AppActionButton(
                    text = "Ver propiedades",
                    onClick = onNavigateProperties,
                    contentColor = Color(0xFF3B251D),
                    gradient = listOf(Color(0xFFFFFFFF), Color(0xFFF1E6D7), Color(0xFFFFFFFF)),
                    animationSeed = 707
                )
            }
        }
    }
}

@Composable
private fun GlowingSurface(
    modifier: Modifier = Modifier,
    corner: androidx.compose.ui.unit.Dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(corner))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0x55DA9C5F), Color(0x229B6C45), Color(0x44F6D2A5), Color(0x33906A49))
                )
            )
            .padding(1.5.dp)
    ) {
        content()
    }
}
