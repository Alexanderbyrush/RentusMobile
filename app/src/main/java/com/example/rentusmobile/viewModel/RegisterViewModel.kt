package com.example.rentusmobile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

class RegisterViewModel : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun register(
        name: String,
        phone: String,
        email: String,
        address: String,
        idDocumento: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModelScope.launch {
            _registerState.value = RegisterState(isLoading = true)

            // Simulación (después conectaremos con API)
            kotlinx.coroutines.delay(1000)

            if (password == passwordConfirmation &&
                name.isNotBlank() && email.isNotBlank()) {
                _registerState.value = RegisterState(isSuccess = true)
            } else {
                _registerState.value = RegisterState(
                    error = "Verifica los datos. Las contraseñas deben coincidir."
                )
            }
        }
    }

    fun resetState() {
        _registerState.value = RegisterState()
    }
}