package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.animation.AnimatedBackground
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
    val status: String
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
    onNavigatePayments: () -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todas") }
    var carouselIndex by remember { mutableIntStateOf(0) }

    val filters = listOf("Todas", "Apartamento", "Casa", "Arriendo", "Venta")
    val featured = listOf(
        "Penthouse con vista panorámica",
        "Casa familiar en zona residencial",
        "Apartamento moderno en el centro"
    )
    val properties = listOf(
        PropertyCardItem("Apartamento Laureles", "Medellín", "$2.200.000 / mes", "85m²", "3 hab", "2 baños", "Disponible"),
        PropertyCardItem("Casa Campestre", "Rionegro", "$620.000.000", "210m²", "4 hab", "3 baños", "Venta"),
        PropertyCardItem("Studio Ejecutivo", "Bogotá", "$1.800.000 / mes", "45m²", "1 hab", "1 baño", "Disponible"),
        PropertyCardItem("Loft Industrial", "Cali", "$2.950.000 / mes", "72m²", "2 hab", "2 baños", "Nuevo")
    )

    LaunchedEffect(featured.size) {
        while (true) {
            delay(3400)
            carouselIndex = (carouselIndex + 1) % featured.size
        }
    }

    val uiState = when {
        query == "loading" -> PropertiesUiState.Loading
        query == "error" -> PropertiesUiState.Error
        query == "empty" -> PropertiesUiState.Empty
        else -> PropertiesUiState.Success
    }

    AnimatedBackground(
        colors = listOf(Color(0xFFF8F5F2), Color(0xFFF4EEE7), Color(0xFFF8F5F2)),
        blobColors = listOf(
            Color(0xFF3B251D).copy(alpha = 0.05f),
            Color(0xFFDA9C5F).copy(alpha = 0.06f),
            Color.White.copy(alpha = 0.07f)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                PropertiesUiState.Loading -> LoadingState()
                PropertiesUiState.Error -> CenterInfo("No pudimos cargar las propiedades.") {
                    AppActionButton(text = "Reintentar", onClick = {}, modifier = Modifier.fillMaxWidth(0.52f))
                }
                PropertiesUiState.Empty -> CenterInfo("No encontramos resultados para tu búsqueda.") {
                    Icon(Icons.Default.HourglassBottom, contentDescription = null, tint = Color(0xFF8A5D34), modifier = Modifier.size(30.dp))
                }
                PropertiesUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(bottom = 84.dp),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item { AnimatedHeading("Explora propiedades", style = TextStyle(fontSize = 28.sp)) }
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
                                placeholder = { Text("Buscar por ciudad, barrio o tipo") },
                                shape = RoundedCornerShape(14.dp),
                                singleLine = true
                            )
                        }
                        item {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                filters.forEach { filter ->
                                    val selected = selectedFilter == filter
                                    val scale by animateFloatAsState(if (selected) 1.06f else 1f, label = "chipScale")
                                    AssistChip(
                                        modifier = Modifier.scale(scale),
                                        onClick = { selectedFilter = filter },
                                        label = { Text(filter) },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = if (selected) Color(0xFF3B251D) else Color.White,
                                            labelColor = if (selected) Color.White else Color(0xFF3B251D)
                                        )
                                    )
                                }
                            }
                        }
                        item { Text("${properties.size} propiedades encontradas", color = Color(0xFF6B7280), fontSize = 13.sp) }
                        item {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                userScrollEnabled = false,
                                modifier = Modifier.height(500.dp)
                            ) {
                                itemsIndexed(properties) { index, property ->
                                    AnimatedVisibility(
                                        visible = true,
                                        enter = fadeIn(initialAlpha = 0.25f) + scaleIn(initialScale = 0.92f)
                                    ) {
                                        PropertyCard(property, index)
                                    }
                                }
                            }
                        }
                        item {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                AppActionButton(text = "Página 1 de 8", onClick = {}, modifier = Modifier.weight(1f), gradient = listOf(Color.White, Color.White, Color.White), contentColor = Color(0xFF6B7280))
                                AppActionButton(text = "Siguiente", onClick = {}, modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            HomeNavbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedTab = "Propiedades",
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
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp),
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
    Box(modifier = Modifier.fillMaxWidth().height(220.dp).clip(RoundedCornerShape(18.dp))) {
        Image(painter = painterResource(id = R.drawable.casa), contentDescription = title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xB0000000)))))
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(14.dp)) {
            Text("Destacado", color = Color(0xFFF6D2A5), fontWeight = FontWeight.Bold)
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("${index + 1} / $count", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
        }
        Row(modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.background(Color.White.copy(alpha = 0.2f), CircleShape).clickable { onPrev() }.padding(6.dp)) {
                Icon(Icons.Default.ChevronLeft, contentDescription = "Anterior", tint = Color.White)
            }
            Box(modifier = Modifier.background(Color.White.copy(alpha = 0.2f), CircleShape).clickable { onNext() }.padding(6.dp)) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Siguiente", tint = Color.White)
            }
        }
        Row(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(count) { dot ->
                val scale by animateFloatAsState(if (dot == index) 1.35f else 1f, label = "dot")
                Box(modifier = Modifier.size(6.dp).scale(scale).background(if (dot == index) Color(0xFFF6D2A5) else Color.White.copy(alpha = 0.6f), CircleShape))
            }
        }
    }
}

@Composable
private fun PropertyCard(property: PropertyCardItem, index: Int) {
    val scale by animateFloatAsState(targetValue = 1f, label = "card$index")
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(14.dp), modifier = Modifier.scale(scale)) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(modifier = Modifier.fillMaxWidth().height(90.dp).clip(RoundedCornerShape(10.dp))) {
                Image(painter = painterResource(id = R.drawable.casa), contentDescription = property.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                Text(
                    property.status,
                    modifier = Modifier.padding(8.dp).background(Color(0xFF3B251D), RoundedCornerShape(50)).padding(horizontal = 8.dp, vertical = 3.dp),
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
            Text(property.title, fontWeight = FontWeight.Bold, color = Color(0xFF2E1D17), maxLines = 1)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF8B5E34), modifier = Modifier.size(14.dp))
                Text(property.city, color = Color(0xFF6B7280), fontSize = 12.sp)
            }
            Text(property.price, color = Color(0xFF16803C), fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
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
        modifier = Modifier.background(Color(0xFFF6F1EB), RoundedCornerShape(50)).padding(horizontal = 6.dp, vertical = 3.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF8B5E34), modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = 10.sp, color = Color(0xFF6B7280))
    }
}

@Composable
private fun CenterInfo(message: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
        Spacer(modifier = Modifier.height(10.dp))
        Text(message, color = Color(0xFF2E1D17), fontWeight = FontWeight.SemiBold)
    }
}
