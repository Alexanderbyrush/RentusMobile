package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.components.HomeNavbar
import kotlinx.coroutines.delay

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
    onNavigateAbout: () -> Unit = {}
) {
    val properties = remember {
        listOf(
            DemoProperty("Apartamento Premium", "Bogotá", "$2.500.000", "95m²", "3", "2", "Disponible"),
            DemoProperty("Casa Moderna Familiar", "Medellín", "$3.100.000", "140m²", "4", "3", "Disponible"),
            DemoProperty("Loft Ejecutivo", "Cali", "$1.900.000", "70m²", "2", "1", "Mantenimiento")
        )
    }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        ParticlesBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 84.dp)
                .verticalScroll(scrollState)
        ) {
            AnimatedSection(0) {
                HeroSection(scrollState.value)
            }
            AnimatedSection(1) {
                SearchSection()
            }
            AnimatedSection(2) {
                PropertiesSection(properties)
            }
            AnimatedSection(3) {
                CtaSection()
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        HomeNavbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedTab = "Inicio",
            onNavigateHome = onNavigateHome,
            onNavigateProperties = onNavigateProperties,
            onNavigateAbout = onNavigateAbout
        )
    }
}

@Composable
private fun AnimatedSection(index: Int, content: @Composable () -> Unit) {
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(120L * index)
        show = true
    }
    AnimatedVisibility(
        visible = show,
        enter = fadeIn(animationSpec = tween(520)) + slideInVertically(initialOffsetY = { it / 6 }, animationSpec = tween(520))
    ) {
        content()
    }
}

@Composable
private fun ParticlesBackground() {
    val transition = rememberInfiniteTransition(label = "particles")
    val yShift = transition.animateFloat(
        initialValue = 0f,
        targetValue = -1400f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "yShift"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        repeat(20) { index ->
            val size = (8 + (index % 4) * 6).dp
            Box(
                modifier = Modifier
                    .offset(x = (index * 17).dp, y = (1300 + index * 60).dp + yShift.value.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(listOf(Color(0xFF3B251D).copy(alpha = 0.18f), Color(0xFF8B6F47).copy(alpha = 0.12f)))
                    )
            )
        }
    }
}

@Composable
private fun HeroSection(scrollY: Int) {
    val parallax = (scrollY * 0.12f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { translationY = -parallax }
            .background(Brush.linearGradient(listOf(Color(0xFF2E1D17), Color(0xFF3B251D), Color(0xFF4D2F24))))
            .padding(horizontal = 24.dp, vertical = 28.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.12f))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Plataforma líder en arriendos", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
            }

            Text(
                "Encuentra tu próximo hogar\ncon estilo RentUs",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 33.sp,
                lineHeight = 38.sp
            )

            Text(
                "Explora propiedades premium verificadas, con procesos transparentes y soporte experto.",
                color = Color.White.copy(alpha = 0.86f),
                fontSize = 15.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8A97E), contentColor = Color(0xFF2E1D17))
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(17.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Buscar ahora", fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.12f), contentColor = Color.White)
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(17.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Asesoría", fontWeight = FontWeight.SemiBold)
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatChip("1,200+", "Propiedades", Icons.Default.Home)
                StatChip("95%", "Satisfacción", Icons.Default.CheckCircle)
                StatChip("24/7", "Soporte", Icons.Default.Phone)
            }
        }
    }
}

@Composable
private fun StatChip(number: String, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Color.White.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = label, tint = Color(0xFFC8A97E), modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(number, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Text(label, color = Color.White.copy(alpha = 0.84f), fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun SearchSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Buscar propiedades", fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            FilterPill("Ciudad", Icons.Default.LocationOn)
            FilterPill("Tipo", Icons.Default.Apartment)
            FilterPill("Mapa", Icons.Default.Map)
        }
    }
}

@Composable
private fun FilterPill(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (pressed) 0.97f else 1f, label = "pill")
    Surface(
        modifier = Modifier
            .scale(scale)
            .clickable(onClickLabel = text) { pressed = !pressed },
        shape = RoundedCornerShape(50),
        color = if (pressed) Color(0xFF3B251D) else Color.White,
        shadowElevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = if (pressed) Color.White else Color(0xFF3B251D), modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text, color = if (pressed) Color.White else Color(0xFF3B251D), fontSize = 12.sp)
        }
    }
}

@Composable
private fun PropertiesSection(properties: List<DemoProperty>) {
    Column(modifier = Modifier.padding(horizontal = 18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Propiedades destacadas", color = Color(0xFF1F2937), fontWeight = FontWeight.Bold, fontSize = 22.sp)
        properties.forEachIndexed { index, property ->
            val showDelay = 80 * (index + 1)
            var show by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(showDelay.toLong()); show = true }
            AnimatedVisibility(visible = show, enter = fadeIn() + slideInVertically(initialOffsetY = { it / 4 })) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Text(property.title, fontWeight = FontWeight.Bold, color = Color(0xFF1F2937), fontSize = 18.sp)
                            Text(property.price, fontWeight = FontWeight.ExtraBold, color = Color(0xFF27AE60), fontSize = 16.sp)
                        }
                        Text(property.city, color = Color(0xFF6B7280), fontSize = 13.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FeatureMini(Icons.Default.Apartment, property.area)
                            FeatureMini(Icons.Default.Bed, "${property.bedrooms} Hab")
                            FeatureMini(Icons.Default.Bathtub, "${property.bathrooms} Baños")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeatureMini(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Surface(color = Color(0xFFF3F4F6), shape = RoundedCornerShape(50)) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color(0xFF4B5563), modifier = Modifier.size(12.dp))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text, color = Color(0xFF4B5563), fontSize = 11.sp)
        }
    }
}

@Composable
private fun CtaSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
            .background(Brush.linearGradient(listOf(Color(0xFF3B251D), Color(0xFF5B3A2B))), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("¿Listo para mudarte?", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Text("Explora todas las propiedades o comunícate con nuestro equipo.", color = Color.White.copy(alpha = 0.9f))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8A97E), contentColor = Color(0xFF3B251D))) {
                    Text("Ver propiedades", fontWeight = FontWeight.Bold)
                }
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.14f), contentColor = Color.White)) {
                    Text("Contactar")
                }
            }
        }
    }
}
