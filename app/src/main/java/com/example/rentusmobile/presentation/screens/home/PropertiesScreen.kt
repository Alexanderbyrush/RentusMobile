package com.example.rentusmobile.presentation.screens.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.painterResource
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.HomeNavbar

private data class PropertyCardItem(
    val title: String,
    val city: String,
    val price: String,
    val area: String,
    val bedrooms: String,
    val bathrooms: String,
    val status: String
)

private enum class PropertiesUiState {
    Loading,
    Error,
    Empty,
    Success
}

@Composable
fun PropertiesScreen(
    onNavigateHome: () -> Unit = {},
    onNavigateProperties: () -> Unit = {},
    onNavigateAbout: () -> Unit = {}
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

    val uiState = when {
        query == "loading" -> PropertiesUiState.Loading
        query == "error" -> PropertiesUiState.Error
        query == "empty" -> PropertiesUiState.Empty
        else -> PropertiesUiState.Success
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F5F2))) {
        when (uiState) {
            PropertiesUiState.Loading -> CenterInfo("Cargando propiedades...") { CircularProgressIndicator(color = Color(0xFFB8791F)) }
            PropertiesUiState.Error -> CenterInfo("No pudimos cargar las propiedades.") {
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B251D))) {
                    Text("Reintentar")
                }
            }
            PropertiesUiState.Empty -> CenterInfo("No encontramos resultados para tu búsqueda.") {
                Text("Prueba con otro filtro o ciudad.", color = Color(0xFF6B7280))
            }
            PropertiesUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 84.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Explora propiedades",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF2E1D17),
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                                AssistChip(
                                    onClick = { selectedFilter = filter },
                                    label = { Text(filter) },
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = if (selectedFilter == filter) Color(0xFF3B251D) else Color.White,
                                        labelColor = if (selectedFilter == filter) Color.White else Color(0xFF3B251D)
                                    )
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            text = "${properties.size} propiedades encontradas",
                            color = Color(0xFF6B7280),
                            fontSize = 13.sp
                        )
                    }
                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            userScrollEnabled = false,
                            modifier = Modifier.height(490.dp)
                        ) {
                            items(properties) { property ->
                                PropertyCard(property)
                            }
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Página 1 de 8", color = Color(0xFF6B7280))
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B251D))
                            ) {
                                Text("Siguiente")
                            }
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
            onNavigateAbout = onNavigateAbout
        )
    }
}

@Composable
private fun PropertyCarousel(
    title: String,
    index: Int,
    count: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(18.dp))
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
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xB0000000))))
        )
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(14.dp)) {
            Text("Destacado", color = Color(0xFFF6D2A5), fontWeight = FontWeight.Bold)
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("${index + 1} / $count", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
        }
        Row(modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilledTonalIconButton(onClick = onPrev) { Icon(Icons.Default.ChevronLeft, contentDescription = "Anterior") }
            FilledTonalIconButton(onClick = onNext) { Icon(Icons.Default.ChevronRight, contentDescription = "Siguiente") }
        }
        Row(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(count) { dot ->
                Box(
                    modifier = Modifier
                        .size(if (dot == index) 8.dp else 6.dp)
                        .background(if (dot == index) Color(0xFFF6D2A5) else Color.White.copy(alpha = 0.6f), CircleShape)
                )
            }
        }
    }
}

@Composable
private fun PropertyCard(property: PropertyCardItem) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(14.dp)) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.casa),
                    contentDescription = property.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    property.status,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color(0xFF3B251D), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 3.dp),
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
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(Color(0xFFF6F1EB), RoundedCornerShape(50)).padding(horizontal = 6.dp, vertical = 3.dp)) {
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
