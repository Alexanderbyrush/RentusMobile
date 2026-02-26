package com.example.rentusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        get() = email.isNotBlank() && password.length >= 6 && validationErrors.isEmpty()
}

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(value: String) {
        state = state.copy(email = value)
        validateFields()
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
        validateFields()
    }

    fun onTogglePasswordVisibility() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    fun onRememberMeChange(value: Boolean) {
        state = state.copy(rememberMe = value)
    }

    fun onLoginClick(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            kotlinx.coroutines.delay(400)
            state = state.copy(isLoading = false)
            onSuccess()
        }
    }

    fun onForgotPasswordClick() = Unit

    fun onGoogleLoginClick() = Unit

    private fun validateFields(): Map<String, String> {
        val errors = mutableMapOf<String, String>()
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()

        if (state.email.isBlank()) {
            errors["email"] = "El email es obligatorio"
        } else if (!emailRegex.matches(state.email)) {
            errors["email"] = "Formato de email inválido"
        }

        if (state.password.length < 6) {
            errors["password"] = "La contraseña debe tener mínimo 6 caracteres"
        }

        state = state.copy(validationErrors = errors, errorMessage = null)
        return errors
    }
}
