package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onNavigatePayments: () -> Unit = {}
) {
    val properties = remember {
        listOf(
            DemoProperty("Apartamento Premium", "Bogotá", "$2.500.000", "95m²", "3", "2", "Disponible"),
            DemoProperty("Casa Moderna Familiar", "Medellín", "$3.100.000", "140m²", "4", "3", "Disponible"),
            DemoProperty("Loft Ejecutivo", "Cali", "$1.900.000", "70m²", "2", "1", "Mantenimiento")
        )
    }

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
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection()
            SearchSection()
            PropertiesSection(properties, onNavigateProperties)
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
            onNavigatePayments = onNavigatePayments
        )
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
private fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
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
                Text("Propiedades destacadas", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            AnimatedHeading(
                text = "Encuentra tu hogar ideal\nen RentUs",
                style = TextStyle(fontSize = 34.sp, lineHeight = 36.sp)
            )

            Text(
                text = "Explora inmuebles exclusivos, compara precios y agenda visitas fácilmente desde un solo lugar.",
                color = Color(0xFFEFE8DD).copy(alpha = 0.92f),
                fontSize = 15.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatChip("1,200+", "Propiedades", Icons.Default.Home)
                StatChip("980+", "Clientes", Icons.Default.CheckCircle)
                StatChip("1+", "Año", Icons.Default.Star)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.08f))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Map, contentDescription = null, tint = Color.White, modifier = Modifier.size(34.dp))
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

@Composable
private fun StatChip(number: String, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(
        color = Color.White.copy(alpha = 0.1f),
        shape = RoundedCornerShape(14.dp)
    ) {
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .offset(y = (-18).dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Buscar propiedades", fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50))
            SearchInput("Ciudad", Modifier.fillMaxWidth())
            SearchInput("Tipo", Modifier.fillMaxWidth())
            AppActionButton(
                text = "Buscar",
                onClick = {},
                gradient = listOf(Color(0xFF3B251D), Color(0xFF2E1D17), Color(0xFF8A5D34))
            )
        }
    }
}

@Composable
private fun SearchInput(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF9FAFB))
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text(label, color = Color(0xFF6B7280), fontSize = 13.sp)
    }
}

@Composable
private fun PropertiesSection(properties: List<DemoProperty>, onNavigateProperties: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        AnimatedHeading("Propiedades destacadas", style = TextStyle(fontSize = 26.sp))
        Text("Descubre las mejores opciones disponibles para ti.", color = Color(0xFF6B7280))

        properties.forEachIndexed { index, property ->
            val cardScale by animateFloatAsState(targetValue = 1f, label = "cardScale$index")
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier.fillMaxWidth().scale(cardScale)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .background(Brush.linearGradient(listOf(Color(0xFFDED5C4), Color(0xFFC4B5A0))))
                    ) {
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
                                Text(property.title, fontWeight = FontWeight.Bold, color = Color(0xFF1F2937), fontSize = 18.sp)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF6B7280), modifier = Modifier.size(14.dp))
                                    Text(property.city, color = Color(0xFF6B7280), fontSize = 12.sp)
                                }
                            }
                            Text(property.price, fontWeight = FontWeight.ExtraBold, color = Color(0xFF27AE60), fontSize = 18.sp)
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FeatureMini(Icons.Default.Apartment, property.area)
                            FeatureMini(Icons.Default.Bed, property.bedrooms)
                            FeatureMini(Icons.Default.Bathtub, property.bathrooms)
                        }

                        AppActionButton(
                            text = "Ver detalles",
                            onClick = {},
                            gradient = listOf(Color(0xFF4D2F24), Color(0xFF5D3A2D), Color(0xFF7A4E3A))
                        )
                    }
                }
            }
        }

        AppActionButton(text = "Ver todas", onClick = onNavigateProperties)
    }
}

@Composable
private fun FeatureMini(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF9FAFB))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF4B5563), modifier = Modifier.size(13.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(value, fontSize = 12.sp, color = Color(0xFF1F2937), fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun CtaSection(onNavigateProperties: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 22.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF2E1D17), Color(0xFF3B251D))))
            .padding(22.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedHeading("¿Listo para encontrar tu próximo hogar?", style = TextStyle(fontSize = 22.sp))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Explora todas las propiedades o comunícate con nuestro equipo.", color = Color.White.copy(alpha = 0.9f))
            Spacer(modifier = Modifier.height(14.dp))
            AppActionButton(
                text = "Ver propiedades",
                onClick = onNavigateProperties,
                contentColor = Color(0xFF3B251D),
                gradient = listOf(Color(0xFFFFFFFF), Color(0xFFF1E6D7), Color(0xFFFFFFFF))
            )
            AppActionButton(
                text = "Contacto",
                onClick = {},
                gradient = listOf(Color(0xFF5A3A2B), Color(0xFF7A513A), Color(0xFF5A3A2B))
            )
        }
    }
}
