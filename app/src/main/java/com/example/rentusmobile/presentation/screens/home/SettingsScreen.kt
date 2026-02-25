package com.example.rentusmobile.presentation.screens.home

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.presentation.components.AppActionButton
import com.example.rentusmobile.presentation.components.HomeNavbar

private enum class SettingsSection(val label: String, val icon: ImageVector) {
    Profile("Perfil", Icons.Default.Person),
    Security("Seguridad", Icons.Default.Lock),
    Notifications("Notificaciones", Icons.Default.Notifications),
    Preferences("Preferencias", Icons.Default.Tune)
}

private data class NotificationItem(val title: String, val description: String, var enabled: Boolean)

@Composable
fun SettingsScreen(
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
    var activeSection by remember { mutableStateOf(SettingsSection.Profile) }

    var fullName by remember { mutableStateOf("Juan Esteban López") }
    var email by remember { mutableStateOf("juan.lopez@rentus.co") }
    var phone by remember { mutableStateOf("+57 300 123 4567") }
    var document by remember { mutableStateOf("CC 1020xxxxxx") }
    var bio by remember { mutableStateOf("Inversionista inmobiliario y anfitrión de propiedades premium.") }
    var department by remember { mutableStateOf("Cundinamarca") }
    var city by remember { mutableStateOf("Bogotá") }

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val notifications = remember {
        mutableStateListOf(
            NotificationItem("Nuevas solicitudes", "Avisarme cuando llegue una solicitud nueva", true),
            NotificationItem("Recordatorios de pago", "Avisarme 48h antes de vencimientos", true),
            NotificationItem("Mensajes", "Notificar nuevos mensajes del chat", true),
            NotificationItem("Resumen semanal", "Enviar reporte semanal por email", false)
        )
    }

    var language by remember { mutableStateOf("Español") }
    var timezone by remember { mutableStateOf("America/Bogota") }
    var units by remember { mutableStateOf("Métrico") }

    val passwordScore = remember(newPassword, confirmPassword) {
        var score = 0
        if (newPassword.length >= 8) score++
        if (newPassword.any { it.isUpperCase() } && newPassword.any { it.isLowerCase() }) score++
        if (newPassword.any { it.isDigit() }) score++
        if (newPassword.any { !it.isLetterOrDigit() }) score++
        if (newPassword == confirmPassword && confirmPassword.isNotEmpty()) score++
        score
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xFF1A0E0A), Color(0xFF2E1D17), Color(0xFF3B2416))))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("SETTINGS", color = Color(0xFFDA9C5F), fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("Configuración", color = Color(0xFFFFF4E8), fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Text("Administra tu perfil, seguridad y preferencias.", color = Color(0xFFD4C5B9), fontSize = 12.sp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    modifier = Modifier.width(164.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xE63B251D)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SettingsSection.entries.forEach { section ->
                            val selected = section == activeSection
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (selected) Color(0x66DA9C5F) else Color.Transparent,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable { activeSection = section }
                                    .padding(horizontal = 10.dp, vertical = 9.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(section.icon, contentDescription = null, tint = if (selected) Color(0xFFFFE7C7) else Color(0xFFD4C5B9))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(section.label, color = if (selected) Color(0xFFFFE7C7) else Color(0xFFD4C5B9), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color(0xE62E1D17)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        when (activeSection) {
                            SettingsSection.Profile -> item {
                                SectionTitle(Icons.Default.Person, "Perfil")
                                SettingsInput("Nombre completo", fullName) { fullName = it }
                                SettingsInput("Email", email, enabled = false) { email = it }
                                SettingsInput("Teléfono", phone) { phone = it }
                                SettingsInput("Documento", document) { document = it }
                                SettingsInput("Bio", bio) { bio = it }
                                SettingsInput("Departamento", department) { department = it }
                                SettingsInput("Ciudad", city) { city = it }
                                ActionsRow(primaryText = "Guardar cambios", onPrimary = {}, onSecondary = {})
                            }

                            SettingsSection.Security -> item {
                                SectionTitle(Icons.Default.Lock, "Seguridad")
                                SettingsInput("Contraseña actual", currentPassword) { currentPassword = it }
                                SettingsInput("Nueva contraseña", newPassword) { newPassword = it }
                                SettingsInput("Confirmar contraseña", confirmPassword) { confirmPassword = it }

                                val (strengthText, strengthColor) = when {
                                    passwordScore <= 1 -> "Muy débil" to Color(0xFFE74C3C)
                                    passwordScore <= 3 -> "Media" to Color(0xFFF39C12)
                                    else -> "Fuerte" to Color(0xFF2ECC71)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(6.dp)
                                            .background(strengthColor.copy(alpha = 0.35f), RoundedCornerShape(999.dp))
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(strengthText, color = strengthColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                                ActionsRow(primaryText = "Actualizar contraseña", onPrimary = {}, onSecondary = {
                                    currentPassword = ""
                                    newPassword = ""
                                    confirmPassword = ""
                                })
                            }

                            SettingsSection.Notifications -> item {
                                SectionTitle(Icons.Default.Notifications, "Notificaciones")
                                notifications.forEachIndexed { index, item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0x1AFFFFFF), RoundedCornerShape(12.dp))
                                            .padding(horizontal = 10.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(item.title, color = Color(0xFFF0E5DB), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                                            Text(item.description, color = Color(0xFFBFAFA2), fontSize = 11.sp)
                                        }
                                        Switch(
                                            checked = item.enabled,
                                            onCheckedChange = { notifications[index] = item.copy(enabled = it) }
                                        )
                                    }
                                }
                                ActionsRow(primaryText = "Guardar preferencias", onPrimary = {}, onSecondary = {})
                            }

                            SettingsSection.Preferences -> item {
                                SectionTitle(Icons.Default.Tune, "Preferencias")
                                SettingsInputWithIcon("Idioma", language, Icons.Default.Language) { language = it }
                                SettingsInputWithIcon("Zona horaria", timezone, Icons.Default.Public) { timezone = it }
                                SettingsInputWithIcon("Sistema de unidades", units, Icons.Default.Straighten) { units = it }
                                Text(
                                    "* Se removió el selector oscuro/claro como solicitaste.",
                                    color = Color(0xFFC8A97E),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                ActionsRow(primaryText = "Guardar", onPrimary = {}, onSecondary = {})
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            AppActionButton(
                                text = "Cerrar sesión",
                                onClick = {},
                                modifier = Modifier.fillMaxWidth(),
                                gradient = listOf(Color(0xFFE74C3C), Color(0xFFC0392B), Color(0xFFE74C3C))
                            )
                        }
                    }
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
            onNavigateMyReports = onNavigateMyReports,
            onNavigateSettings = onNavigateSettings
        )
    }
}

@Composable
private fun SectionTitle(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color(0xFFDA9C5F), modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(title, color = Color(0xFFFFE7C7), fontSize = 17.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun SettingsInput(label: String, value: String, enabled: Boolean = true, onValueChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = Color(0xFFD4C5B9), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}

@Composable
private fun SettingsInputWithIcon(label: String, value: String, icon: ImageVector, onValueChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = Color(0xFFD4C5B9), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Icon(icon, contentDescription = null) },
            singleLine = true
        )
    }
}

@Composable
private fun ActionsRow(primaryText: String, onPrimary: () -> Unit, onSecondary: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AppActionButton(
            text = "Cancelar",
            onClick = onSecondary,
            modifier = Modifier.weight(1f),
            gradient = listOf(Color(0xFF64748B), Color(0xFF475569), Color(0xFF64748B))
        )
        AppActionButton(
            text = primaryText,
            onClick = onPrimary,
            modifier = Modifier.weight(1f),
            gradient = listOf(Color(0xFFDA9C5F), Color(0xFFB8791F), Color(0xFFDA9C5F))
        )
    }
}
