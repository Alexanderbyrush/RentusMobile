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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextOverflow
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
    val status: String,
    val description: String
)

@Composable
fun HomeScreen(
    onNavigateHome: () -> Unit = {},
    onNavigateProperties: () -> Unit = {},
    onNavigateAbout: () -> Unit = {}
) {
    val properties = remember {
        listOf(
            DemoProperty("Apartamento Premium", "Bogotá", "$2.500.000", "95", "3", "2", "available", "Acabados de lujo, cerca a transporte y zonas verdes."),
            DemoProperty("Casa Moderna Familiar", "Medellín", "$3.100.000", "140", "4", "3", "available", "Casa amplia con patio, sala doble altura y estudio."),
            DemoProperty("Loft Ejecutivo", "Cali", "$1.900.000", "70", "2", "1", "maintenance", "Loft moderno ideal para perfil profesional.")
        )
    }
    var query by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFAFAFA))) {
        ParticlesBackground()

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { ModernHeroSection() }
            item { ModernSearchSection(query = query, onQueryChange = { query = it }) }
            item { PropertiesModernSection(properties = properties, onNavigateProperties = onNavigateProperties) }
            item { CtaModernSection(onNavigateProperties = onNavigateProperties) }
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
private fun ParticlesBackground() {
    val transition = rememberInfiniteTransition(label = "particles")
    val yShift by transition.animateFloat(
        initialValue = 1000f,
        targetValue = -300f,
        animationSpec = infiniteRepeatable(animation = tween(13000, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "yShift"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        repeat(20) { index ->
            Box(
                modifier = Modifier
                    .padding(start = (index * 18).dp)
                    .size((8 + (index % 4) * 4).dp)
                    .offset(y = (yShift + index * 65).dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(Color(0xFF3B251D).copy(alpha = 0.18f), Color(0xFF8B6F47).copy(alpha = 0.12f))))
            )
        }
    }
}

@Composable
private fun ModernHeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp)
            .background(Brush.linearGradient(listOf(Color(0xFF2E1D17), Color(0xFF3B251D), Color(0xFF4D2F24))))
            .padding(horizontal = 20.dp, vertical = 22.dp)
    ) {
        HeroOrbs()

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.clip(RoundedCornerShape(50)).background(Color.White.copy(alpha = 0.1f)).padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(13.dp))
                Spacer(Modifier.width(6.dp))
                Text("Plataforma líder en arriendos", color = Color.White, fontSize = 12.sp)
            }

            AnimatedHeading("Tu próximo hogar\ncon estilo RentUs", style = TextStyle(fontSize = 34.sp, lineHeight = 38.sp))
            Text(
                "Explora propiedades verificadas con procesos claros y soporte premium.",
                color = Color(0xFFEDE8E1),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            HeroStatsInline()
            HeroMapPreview()
        }
    }
}

@Composable
private fun HeroOrbs() {
    val transition = rememberInfiniteTransition(label = "orbs")
    val drift by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(9000), RepeatMode.Reverse), label = "drift")

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.TopEnd).size(140.dp).scale(1f + drift * 0.05f).background(Color(0xFF8B6F47).copy(alpha = 0.25f), CircleShape))
        Box(modifier = Modifier.align(Alignment.CenterStart).size(110.dp).scale(1f - drift * 0.04f).background(Color(0xFFC19A6B).copy(alpha = 0.18f), CircleShape))
        Box(modifier = Modifier.align(Alignment.BottomCenter).size(90.dp).background(Color(0xFFEFE8DD).copy(alpha = 0.12f), CircleShape))
    }
}

@Composable
private fun HeroStatsInline() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        StatInline("1,200+", "Propiedades", Icons.Default.Home)
        StatInline("800+", "Clientes", Icons.Default.CheckCircle)
        StatInline("1+", "Año", Icons.Default.Star)
    }
}

@Composable
private fun StatInline(number: String, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = label, tint = Color(0xFFC8A97E), modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Column {
                Text(number, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Text(label, color = Color.White.copy(alpha = 0.82f), fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun HeroMapPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Brush.linearGradient(listOf(Color.White.copy(alpha = 0.06f), Color(0xFF0D1A32).copy(alpha = 0.5f))))
    ) {
        Row(modifier = Modifier.align(Alignment.TopEnd).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).background(Color(0xFF22C55E), CircleShape))
            Spacer(Modifier.width(6.dp))
            Text("En vivo", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(12.dp).clip(RoundedCornerShape(50)).background(Color.White.copy(alpha = 0.14f)).padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(6.dp))
            Text("Explorar en mapa", color = Color.White, fontSize = 12.sp)
        }

        FloatingHeroCard(text = "Propiedades verificadas", icon = Icons.Default.Home, align = Alignment.TopStart)
        FloatingHeroCard(text = "Excelente calificación", icon = Icons.Default.Star, align = Alignment.CenterEnd)
    }
}

@Composable
private fun FloatingHeroCard(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, align: Alignment) {
    Card(
        modifier = Modifier.align(align).padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color(0xFF3B251D), modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(6.dp))
            Text(text, color = Color(0xFF2C3E50), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun ModernSearchSection(query: String, onQueryChange: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF3B251D))
                Spacer(Modifier.width(6.dp))
                Text("Buscador inteligente", fontWeight = FontWeight.Bold, color = Color(0xFF2C3E50))
            }

            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por ciudad, barrio o tipo") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) }
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                ChipFilter("Ciudad", Icons.Default.LocationOn, Modifier.weight(1f))
                ChipFilter("Tipo", Icons.Default.Apartment, Modifier.weight(1f))
                ChipFilter("Mapa", Icons.Default.Map, Modifier.weight(1f))
            }

            AppActionButton(text = "Buscar", onClick = {}, gradient = listOf(Color(0xFF3B251D), Color(0xFF2E1D17), Color(0xFF8B5E34)))
        }
    }
}

@Composable
private fun ChipFilter(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    var active by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (active) 1.03f else 1f, label = "chip")

    Card(
        modifier = modifier.scale(scale).clickable { active = !active },
        colors = CardDefaults.cardColors(containerColor = if (active) Color(0xFF3B251D) else Color(0xFFF8F8F8)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 9.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(icon, contentDescription = null, tint = if (active) Color.White else Color(0xFF3B251D), modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(5.dp))
            Text(text, color = if (active) Color.White else Color(0xFF3B251D), fontSize = 12.sp)
        }
    }
}

@Composable
private fun PropertiesModernSection(properties: List<DemoProperty>, onNavigateProperties: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text("Propiedades destacadas", color = Color(0xFF4D2F24), fontWeight = FontWeight.Bold)
        }
        AnimatedHeading("Explora opciones premium", style = TextStyle(fontSize = 26.sp))

        properties.forEachIndexed { index, property ->
            PropertyCardModern(property = property, index = index)
        }

        AppActionButton(text = "Ver todas las propiedades", onClick = onNavigateProperties)
    }
}

@Composable
private fun PropertyCardModern(property: DemoProperty, index: Int) {
    val showScale by animateFloatAsState(targetValue = 1f, label = "card$index")
    Card(
        modifier = Modifier.fillMaxWidth().scale(showScale),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(property.title, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1F2937), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF6B7280), modifier = Modifier.size(13.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(property.city, color = Color(0xFF6B7280), fontSize = 12.sp)
                    }
                }
                Text(property.price, color = Color(0xFF16803C), fontWeight = FontWeight.ExtraBold)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                PropertyFeature("${property.area} m²", Icons.Default.Apartment, Modifier.weight(1f))
                PropertyFeature(property.bedrooms, Icons.Default.Bed, Modifier.weight(1f))
                PropertyFeature(property.bathrooms, Icons.Default.Bathtub, Modifier.weight(1f))
            }

            Text(property.description, color = Color(0xFF6B7280), fontSize = 12.sp, maxLines = 2)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                AppActionButton(
                    text = "Ver detalle",
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    gradient = listOf(Color(0xFF3B251D), Color(0xFF5A3728), Color(0xFF7F5944))
                )
                SmallAction(Icons.Default.Favorite)
                SmallAction(Icons.Default.Share)
                SmallAction(Icons.Default.ArrowForward)
            }
        }
    }
}

@Composable
private fun PropertyFeature(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC))) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(icon, contentDescription = null, tint = Color(0xFF3B251D), modifier = Modifier.size(13.dp))
            Spacer(Modifier.width(4.dp))
            Text(text, fontSize = 11.sp, color = Color(0xFF4B5563))
        }
    }
}

@Composable
private fun SmallAction(icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        modifier = Modifier.size(42.dp).clickable { },
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F1EB))
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Icon(icon, contentDescription = null, tint = Color(0xFF3B251D), modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun CtaModernSection(onNavigateProperties: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF2E1D17), Color(0xFF3B251D), Color(0xFF4D2F24))))
            .padding(18.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFC8A97E), modifier = Modifier.size(28.dp))
            AnimatedHeading("¿Listo para encontrar hogar?", style = TextStyle(fontSize = 28.sp))
            Text("Explora propiedades verificadas o habla con un asesor.", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                AppActionButton(
                    text = "Ver propiedades",
                    onClick = onNavigateProperties,
                    modifier = Modifier.weight(1f),
                    contentColor = Color(0xFF1F130E),
                    gradient = listOf(Color(0xFFDA9C5F), Color(0xFFE4B782), Color(0xFFC08649))
                )
                AppActionButton(
                    text = "Contactar",
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    gradient = listOf(Color(0xFF3B251D), Color(0xFF5B3A2B), Color(0xFF7A513A))
                )
            }
        }
    }
}
