package com.example.rentusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentusmobile.data.models.RegisterData
import com.example.rentusmobile.data.repository.AuthRepository
import com.example.rentusmobile.utils.Resource
import com.example.rentusmobile.utils.getValidationErrors
import kotlinx.coroutines.launch

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val idDocument: String = "",
    val address: String = "",
    val password: String = "",
    val acceptTerms: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val validationErrors: Map<String, String> = emptyMap(),
    val isPasswordVisible: Boolean = false
) {
    val isFormValid: Boolean
        get() = validationErrors.isEmpty() && name.isNotBlank() && email.isNotBlank() &&
            phone.isNotBlank() && idDocument.isNotBlank() && address.isNotBlank() &&
            password.length >= 6 && acceptTerms
}

class RegisterViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun onNameChange(value: String) {
        state = state.copy(name = value)
        validateFields()
    }

    fun onEmailChange(value: String) {
        state = state.copy(email = value)
        validateFields()
    }

    fun onPhoneChange(value: String) {
        state = state.copy(phone = value)
        validateFields()
    }

    fun onIdDocumentChange(value: String) {
        state = state.copy(idDocument = value)
        validateFields()
    }

    fun onAddressChange(value: String) {
        state = state.copy(address = value)
        validateFields()
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
        validateFields()
    }

    fun onTogglePasswordVisibility() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    fun onAcceptTermsChange(value: Boolean) {
        state = state.copy(acceptTerms = value)
        validateFields()
    }

    fun onRegisterClick(onSuccess: () -> Unit = {}) {
        val errors = validateFields()
        if (errors.isNotEmpty()) return

        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            when (val result = authRepository.register(
                RegisterData(
                    name = state.name,
                    email = state.email,
                    phone = state.phone,
                    idDocument = state.idDocument,
                    address = state.address,
                    password = state.password
                )
            )) {
                is Resource.Success -> {
                    onSuccess()
                    state = state.copy(isLoading = false)
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message,
                        validationErrors = result.getValidationErrors().orEmpty()
                    )
                }

                Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }
    }

    fun onGoogleRegisterClick() = Unit

    fun onTermsClick() = Unit

    private fun validateFields(): Map<String, String> {
        val errors = mutableMapOf<String, String>()
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()
        val phoneDigits = state.phone.filter { it.isDigit() }

        if (state.name.trim().length < 3) errors["name"] = "Ingresa un nombre válido (mínimo 3 caracteres)"
        if (state.email.isBlank()) {
            errors["email"] = "El email es obligatorio"
        } else if (!emailRegex.matches(state.email)) {
            errors["email"] = "Formato de email inválido"
        }
        if (phoneDigits.length < 10) errors["phone"] = "El teléfono debe tener al menos 10 dígitos"
        if (state.idDocument.isBlank()) errors["idDocument"] = "El documento es obligatorio"
        if (state.address.isBlank()) errors["address"] = "La dirección es obligatoria"
        if (state.password.length < 6) errors["password"] = "La contraseña debe tener mínimo 6 caracteres"
        if (!state.acceptTerms) errors["terms"] = "Debes aceptar los términos y condiciones"

        state = state.copy(validationErrors = errors, errorMessage = null)
        return errors
    }
}
