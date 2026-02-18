package com.example.rentusmobile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)

            // Aquí irá la lógica real cuando conectemos con API
            // Por ahora solo simulamos
            kotlinx.coroutines.delay(1000)

            if (email.isNotBlank() && password.isNotBlank()) {
                _loginState.value = LoginState(isSuccess = true)
            } else {
                _loginState.value = LoginState(error = "Correo o contraseña incorrectos.")
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState()
    }
}