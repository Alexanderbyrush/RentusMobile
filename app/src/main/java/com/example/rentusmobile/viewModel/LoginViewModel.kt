package com.example.rentusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class LoginState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val validationErrors: Map<String, String> = emptyMap(),
    val isPasswordVisible: Boolean = false
) {
    val isFormValid: Boolean
        get() = true
}

class LoginViewModel(
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(value: String) {
        state = state.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
    }

    fun onTogglePasswordVisibility() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    fun onRememberMeChange(value: Boolean) {
        state = state.copy(rememberMe = value)
    }

    fun onLoginClick(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null, validationErrors = emptyMap())
            delay(350)
            onSuccess()
            state = state.copy(isLoading = false)
        }
    }

    fun onForgotPasswordClick() = Unit

    fun onGoogleLoginClick() = Unit
}
