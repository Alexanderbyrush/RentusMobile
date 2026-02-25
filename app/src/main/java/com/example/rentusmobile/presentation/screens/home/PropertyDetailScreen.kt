package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

@Composable
fun PropertyDetailScreen(
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
    onNavigateSettings: () -> Unit = {},
    onNavigateEdit: () -> Unit = {}
) {
    var imageIndex by remember { mutableIntStateOf(0) }
    val gallery = remember { listOf(R.drawable.casa, R.drawable.casa, R.drawable.casa) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 14.dp).padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("PROPERTY DETAIL", color = Color(0xFFDA9C5F), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("Penthouse Sky Lounge", color = Color(0xFFFFF4E8), fontSize = 29.sp, fontWeight = FontWeight.ExtraBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("El Poblado, Medellín", color = Color(0xFFD4C5B9), fontSize = 12.sp)
                }
            }

            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)), shape = RoundedCornerShape(16.dp)) {
                    Box(modifier = Modifier.fillMaxWidth().height(220.dp).padding(10.dp).clip(RoundedCornerShape(12.dp))) {
                        Crossfade(targetState = imageIndex, label = "detailImage") { idx ->
                            Image(
                                painter = painterResource(gallery[idx]),
                                contentDescription = "Property image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Row(
                            modifier = Modifier.align(Alignment.Center).fillMaxWidth().padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(Icons.Default.ChevronLeft, null, tint = Color.White, modifier = Modifier
                                .background(Color(0x77000000), RoundedCornerShape(50))
                                .clickable { if (imageIndex > 0) imageIndex-- }
                                .padding(8.dp))
                            Icon(Icons.Default.ChevronRight, null, tint = Color.White, modifier = Modifier
                                .background(Color(0x77000000), RoundedCornerShape(50))
                                .clickable { if (imageIndex < gallery.lastIndex) imageIndex++ }
                                .padding(8.dp))
                        }
                        Row(modifier = Modifier.align(Alignment.TopStart).padding(10.dp).background(Color(0xAA2ECC71), RoundedCornerShape(50)).padding(horizontal = 8.dp, vertical = 3.dp)) {
                            Icon(Icons.Default.Star, null, tint = Color.White, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Disponible", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)), shape = RoundedCornerShape(16.dp)) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                        Text("$6.200.000 / mes", color = Color(0xFFDA9C5F), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Vista panorámica, acabados de lujo y amenities premium.", color = Color(0xFFE8D7C8), fontSize = 13.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FeatureChip(Icons.Default.Bed, "4 hab")
                            FeatureChip(Icons.Default.Bathtub, "4 baños")
                            FeatureChip(Icons.Default.Star, "220m²")
                        }
                        AppActionButton(
                            text = "Editar propiedad",
                            onClick = onNavigateEdit,
                            modifier = Modifier.fillMaxWidth(),
                            gradient = listOf(Color(0xFF6366F1), Color(0xFF4F46E5), Color(0xFF6366F1))
                        )
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
private fun FeatureChip(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        modifier = Modifier.background(Color(0x33DA9C5F), RoundedCornerShape(50)).padding(horizontal = 8.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, color = Color(0xFFF0E5DB), fontSize = 11.sp)
    }
}
