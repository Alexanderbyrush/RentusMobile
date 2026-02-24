package com.example.rentusmobile.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavbar(
    modifier: Modifier = Modifier,
    selectedTab: String = "Inicio",
    onNavigateHome: () -> Unit = {},
    onNavigateProperties: () -> Unit = {},
    onNavigateAbout: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
    onNavigateContracts: () -> Unit = {}
) {
    var openMenu by remember { mutableStateOf(false) }

    val quickLinks = listOf(
        Triple("Inicio", Icons.Default.Home, onNavigateHome),
        Triple("Propiedades", Icons.Default.Apartment, onNavigateProperties),
        Triple("Nosotros", Icons.Default.Person, onNavigateAbout)
    )

    val menuItems = listOf(
        Triple("Mi Perfil", Icons.Default.Person, onNavigateProfile),
        Triple("Notificaciones", Icons.Default.Notifications, onNavigateNotifications),
        Triple("Contratos", Icons.Default.Apartment, onNavigateContracts),
        Triple("Pagos", Icons.Default.Payments, {}),
        Triple("Mantenimiento", Icons.Default.Build, {}),
        Triple("Mis Solicitudes", Icons.Default.Sms, {}),
        Triple("Ajustes", Icons.Default.Settings, {})
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(86.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xCC3B251D),
                        Color(0xD92E1D17)
                    )
                ),
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            quickLinks.forEach { (label, icon, action) ->
                val isActive = selectedTab == label
                MorphingNavItem(
                    label = label,
                    icon = icon,
                    selected = isActive,
                    onClick = action
                )
            }
        }

        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    Brush.radialGradient(listOf(Color(0xFFDA9C5F), Color(0xFF8A5D34))),
                    CircleShape
                )
                .clickable { openMenu = true },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }
    }

    if (openMenu) {
        ModalBottomSheet(
            onDismissRequest = { openMenu = false },
            containerColor = Color(0xFFFAFAFA)
        ) {
            Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)) {
                Text("Opciones", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF2C3E50))
                Spacer(modifier = Modifier.height(8.dp))
                menuItems.forEach { (label, icon, action) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                openMenu = false
                                action()
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0xFFF3E3D0), RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(icon, contentDescription = label, tint = Color(0xFF3B251D))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(label, color = Color(0xFF2C3E50), fontSize = 15.sp)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun MorphingNavItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = when {
            pressed -> 0.92f
            selected -> 1.08f
            else -> 1f
        },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "itemScale"
    )
    val iconTint by animateColorAsState(
        targetValue = if (selected) Color(0xFFDA9C5F) else Color.White.copy(alpha = 0.78f),
        label = "iconTint"
    )
    val bubbleWidth by animateDpAsState(
        targetValue = if (selected) 52.dp else 38.dp,
        label = "bubbleWidth"
    )

    Column(
        modifier = Modifier
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(bubbleWidth)
                .height(34.dp)
                .background(
                    if (selected) Brush.horizontalGradient(listOf(Color(0x99DA9C5F), Color(0x663B251D)))
                    else Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent)),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = selected, label = "iconMorph") { state ->
                Icon(
                    icon,
                    contentDescription = label,
                    tint = if (state) Color(0xFFFFE7C7) else iconTint,
                    modifier = Modifier.size(if (state) 24.dp else 22.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        AnimatedVisibility(visible = selected) {
            Text(label, color = Color(0xFFFFE7C7), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
        }
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .width(if (selected) 20.dp else 6.dp)
                .height(3.dp)
                .background(
                    if (selected) Brush.horizontalGradient(listOf(Color(0xFFFFD59A), Color(0xFFDA9C5F))) else Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent)),
                    CircleShape
                )
        )
    }
}
