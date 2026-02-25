package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.animation.ShimmerBlock
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar
import kotlinx.coroutines.delay

private data class PropertyCardItem(
    val title: String,
    val city: String,
    val price: String,
    val area: String,
    val bedrooms: String,
    val bathrooms: String,
    val status: String,
    val badge: String
)

private enum class PropertiesUiState { Loading, Error, Empty, Success }

@Composable
fun PropertiesScreen(
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
    var query by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todas") }
    var carouselIndex by remember { mutableIntStateOf(0) }

    val filters = remember { listOf("Todas", "Apartamento", "Casa", "Arriendo", "Venta", "Premium") }
    val featured = remember {
        listOf(
            "Penthouse Sky Lounge",
            "Villa Designer 2026",
            "Loft Smart Living"
        )
    }
    val properties = remember {
        listOf(
            PropertyCardItem("Penthouse Sky Lounge", "Medellín", "$6.200.000 / mes", "220m²", "4 hab", "4 baños", "Disponible", "TOP"),
            PropertyCardItem("Villa Lake Side", "Rionegro", "$1.250.000.000", "420m²", "5 hab", "6 baños", "Venta", "NEW"),
            PropertyCardItem("Loft Neon District", "Bogotá", "$3.100.000 / mes", "92m²", "2 hab", "2 baños", "Disponible", "HOT"),
            PropertyCardItem("Casa Forest Minimal", "Cali", "$4.700.000 / mes", "260m²", "4 hab", "4 baños", "Nuevo", "TREND")
        )
    }

    LaunchedEffect(featured.size) {
        while (true) {
            delay(3200)
            carouselIndex = (carouselIndex + 1) % featured.size
        }
    }

    val uiState = when {
        query.equals("loading", true) -> PropertiesUiState.Loading
        query.equals("error", true) -> PropertiesUiState.Error
        query.equals("empty", true) -> PropertiesUiState.Empty
        else -> PropertiesUiState.Success
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF0D0A09), Color(0xFF241711), Color(0xFF3B251D))))
    ) {
        FuturisticBackground()

        when (uiState) {
            PropertiesUiState.Loading -> LoadingState()
            PropertiesUiState.Error -> CenterInfo("No pudimos cargar las propiedades.") {
                AppActionButton(text = "Reintentar", onClick = {}, modifier = Modifier.fillMaxWidth(0.52f))
            }
            PropertiesUiState.Empty -> CenterInfo("No encontramos resultados para tu búsqueda.") {
                Icon(Icons.Default.HourglassBottom, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(30.dp))
            }
            PropertiesUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 84.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { HeroBlock() }
                    item {
                        PropertyCarousel(
                            title = featured[carouselIndex],
                            index = carouselIndex,
                            count = featured.size,
                            onPrev = { carouselIndex = (carouselIndex - 1 + featured.size) % featured.size },
                            onNext = { carouselIndex = (carouselIndex + 1) % featured.size }
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            placeholder = { Text("Busca por ciudad, tipo o mood de vivienda") },
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true
                        )
                    }
                    item {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            filters.forEach { filter ->
                                val selected = selectedFilter == filter
                                val scale by animateFloatAsState(if (selected) 1.08f else 1f, label = "chipScale")
                                AssistChip(
                                    modifier = Modifier.scale(scale),
                                    onClick = { selectedFilter = filter },
                                    label = { Text(filter) },
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = if (selected) Color(0xFFDA9C5F) else Color(0x1FFFFFFF),
                                        labelColor = if (selected) Color(0xFF1A0E0A) else Color(0xFFF0E5DB)
                                    )
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            "${properties.size} propiedades premium encontradas",
                            color = Color(0xFFE8DAC8),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            userScrollEnabled = false,
                            modifier = Modifier.height(560.dp)
                        ) {
                            itemsIndexed(properties) { index, property ->
                                AnimatedVisibility(
                                    visible = true,
                                    enter = fadeIn(initialAlpha = 0.22f) + scaleIn(initialScale = 0.9f)
                                ) {
                                    PropertyCard(property = property, index = index)
                                }
                            }
                        }
                    }
                }
            }
        }

        HomeNavbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(4f),
            selectedTab = "Propiedades",
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
private fun HeroBlock() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x223B251D))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color(0xFFDA9C5F))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Colección 2026", color = Color(0xFFDA9C5F), fontWeight = FontWeight.Bold)
            }
            AnimatedHeading("Propiedades que parecen de otro nivel", style = TextStyle(fontSize = 30.sp, lineHeight = 32.sp))
            Text(
                "Explora una experiencia más cinematográfica: cards vivas, navegación fluida y visual premium.",
                color = Color(0xFFE8DAC8),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ShimmerBlock(heightDp = 220)
        ShimmerBlock(heightDp = 52)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(3) { ShimmerBlock(modifier = Modifier.weight(1f), heightDp = 36) }
        }
        repeat(4) { ShimmerBlock(heightDp = 120) }
    }
}

@Composable
private fun PropertyCarousel(title: String, index: Int, count: Int, onPrev: () -> Unit, onNext: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.casa),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xB0000000),
                            Color(0xD90D0A09)
                        )
                    )
                )
        )
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFF6D2A5), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Featured Drop", color = Color(0xFFF6D2A5), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
            AnimatedHeading(title, style = TextStyle(fontSize = 18.sp), gradientColors = listOf(Color(0xFFFFF7EE), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 3100)
            Text("${index + 1} / $count", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
        }

        Row(modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GlassArrow(Icons.Default.ChevronLeft, onPrev, "Anterior")
            GlassArrow(Icons.Default.ChevronRight, onNext, "Siguiente")
        }
    }
}

@Composable
private fun GlassArrow(icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit, desc: String) {
    Box(
        modifier = Modifier
            .background(Color.White.copy(alpha = 0.18f), CircleShape)
            .clickable { onClick() }
            .padding(6.dp)
    ) {
        Icon(icon, contentDescription = desc, tint = Color.White)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PropertyCard(property: PropertyCardItem, index: Int) {
    val entryScale by animateFloatAsState(targetValue = 1f, animationSpec = tween(450 + index * 60, easing = FastOutSlowInEasing), label = "card$index")

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E140F)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.scale(entryScale)
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.casa),
                    contentDescription = property.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .background(Color(0xCC0D0A09), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(property.badge, color = Color(0xFFDA9C5F), fontSize = 10.sp, fontWeight = FontWeight.ExtraBold)
                }
                Text(
                    property.status,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color(0xCC2ECC71), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            AnimatedHeading(property.title, style = TextStyle(fontSize = 16.sp), gradientColors = listOf(Color(0xFFFFF4E8), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 3000)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(14.dp))
                Text(property.city, color = Color(0xFFBCA99A), fontSize = 12.sp)
            }
            Text(property.price, color = Color(0xFF2ECC71), fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)

            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                MiniFeature(Icons.Default.Apartment, property.area)
                MiniFeature(Icons.Default.Bed, property.bedrooms)
                MiniFeature(Icons.Default.Bathtub, property.bathrooms)
            }
        }
    }
}

@Composable
private fun MiniFeature(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color(0x33DA9C5F), RoundedCornerShape(50))
            .padding(horizontal = 6.dp, vertical = 3.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = 10.sp, color = Color(0xFFE7D8C8))
    }
}

@Composable
private fun CenterInfo(message: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
        Spacer(modifier = Modifier.height(10.dp))
        Text(message, color = Color(0xFFF0E5DB), fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun FuturisticBackground() {
    val transition = rememberInfiniteTransition(label = "props-bg")
    val shift by transition.animateFloat(
        initialValue = 0f,
        targetValue = -24f,
        animationSpec = infiniteRepeatable(tween(2200, easing = LinearEasing), RepeatMode.Reverse),
        label = "bg-shift"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        repeat(18) { i ->
            Box(
                modifier = Modifier
                    .padding(start = (20 + i * 20).dp, top = (40 + i * 45).dp + shift.dp)
                    .size((3 + i % 3).dp)
                    .clip(CircleShape)
                    .background(Color(0x44DA9C5F))
            )
        }
    }
}
