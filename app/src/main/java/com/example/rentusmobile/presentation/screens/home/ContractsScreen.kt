package com.example.rentusmobile.presentation.screens.home

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.components.AnimatedHeading
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar
import androidx.compose.ui.res.painterResource

data class ContractItem(val id: Int, val title: String, val address: String, val status: String, val price: String)

@Composable
fun ContractsScreen(
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
    onNavigateMyReports: () -> Unit = {}
) {
    val contracts = remember {
        listOf(
            ContractItem(212, "Contrato Torre Alta", "Bogotá - Chapinero", "Activo", "$2.500.000"),
            ContractItem(213, "Contrato Vista Sol", "Medellín - Laureles", "Pendiente", "$3.100.000"),
            ContractItem(214, "Contrato Gran Reserva", "Cali - El Peñón", "Activo", "$1.900.000")
        )
    }
    var active by remember { mutableIntStateOf(0) }
    var showPreview by remember { mutableIntStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))) {
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 84.dp).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            AnimatedHeading("Contratos", style = androidx.compose.ui.text.TextStyle(fontSize = 30.sp), gradientColors = listOf(Color(0xFFFFF2E0), Color(0xFFF6D2A5), Color(0xFFDA9C5F)), durationMillis = 2800)
            Text("Gestiona y revisa tus contratos con animaciones y acciones rápidas.", color = Color(0xFFD4C5B9), fontSize = 13.sp)

            contracts.forEachIndexed { index, contract ->
                val scale by animateFloatAsState(targetValue = if (active == index) 1f else 0.95f, label = "cardScale")
                Card(
                    modifier = Modifier.fillMaxWidth().scale(scale).clickable { active = index },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xF23A2318)),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (active == index) 10.dp else 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.casa),
                            contentDescription = contract.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().height(150.dp)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(contract.title, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                            Text(contract.status, color = if (contract.status == "Activo") Color(0xFF2ECC71) else Color(0xFFF39C12), fontWeight = FontWeight.Bold)
                        }
                        Text(contract.address, color = Color(0xFFD4C5B9), fontSize = 12.sp)
                        Text("${contract.price} / mes", color = Color(0xFFDA9C5F), fontWeight = FontWeight.ExtraBold)

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            AppActionButton(
                                text = "Vista previa",
                                onClick = { showPreview = index },
                                modifier = Modifier.weight(1f),
                                gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F))
                            )
                            Box(modifier = Modifier.size(52.dp).background(Brush.linearGradient(listOf(Color(0xFF3498DB), Color(0xFF2980B9))), RoundedCornerShape(14.dp)).clickable { }, contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Download, contentDescription = null, tint = Color.White)
                            }
                        }
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(44.dp).background(Color(0xAA562C1D), RoundedCornerShape(22.dp)).clickable { if (active > 0) active-- }, contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("${active + 1} / ${contracts.size}", color = Color(0xFFF0E5DB), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.size(44.dp).background(Color(0xAA562C1D), RoundedCornerShape(22.dp)).clickable { if (active < contracts.lastIndex) active++ }, contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White)
                }
            }
        }

        HomeNavbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedTab = "",
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
            onNavigateMyReports = onNavigateMyReports
        )

        if (showPreview >= 0) {
            val item = contracts[showPreview]
            Dialog(onDismissRequest = { showPreview = -1 }) {
                Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF2E1D17))) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Description, contentDescription = null, tint = Color(0xFFDA9C5F))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(item.title, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Text("Dirección: ${item.address}", color = Color(0xFFD4C5B9), fontSize = 13.sp)
                        Text("Estado: ${item.status}", color = Color(0xFFD4C5B9), fontSize = 13.sp)
                        Text("Valor: ${item.price}", color = Color(0xFFDA9C5F), fontWeight = FontWeight.Bold)
                        AppActionButton(text = "Cerrar", onClick = { showPreview = -1 }, gradient = listOf(Color(0xFFE74C3C), Color(0xFFC0392B), Color(0xFFE74C3C)))
                    }
                }
            }
        }
    }
}
