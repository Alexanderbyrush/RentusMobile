package com.example.rentusmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.rentusmobile.presentation.screens.auth.LoginScreen
import com.example.rentusmobile.presentation.screens.auth.RegisterScreen
import com.example.rentusmobile.presentation.theme.RentusMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentusMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Cambia entre LoginScreen y RegisterScreen para ver cada una
                    LoginScreen(
                        onNavigateToRegister = { /* Por ahora vacío */ },
                        onNavigateToHome = { /* Por ahora vacío */ },
                        onBackPressed = { /* Por ahora vacío */ }
                    )

                    // Para ver Register, comenta la de arriba y descomenta esta:
                    /*
                    RegisterScreen(
                        onNavigateToLogin = { /* Por ahora vacío */ },
                        onNavigateToHome = { /* Por ahora vacío */ },
                        onBackPressed = { /* Por ahora vacío */ }
                    )
                    */
                }
            }
        }
    }
}