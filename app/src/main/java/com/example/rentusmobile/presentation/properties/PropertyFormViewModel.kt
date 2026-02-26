package com.example.rentusmobile.presentation.properties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rentusmobile.core.common.Resource
import com.example.rentusmobile.domain.model.Property
import com.example.rentusmobile.domain.repository.PropertiesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PropertyFormViewModel(
    private val repository: PropertiesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PropertyFormUiState())
    val uiState: StateFlow<PropertyFormUiState> = _uiState.asStateFlow()

    fun onFieldChange(reducer: PropertyFormUiState.() -> PropertyFormUiState) {
        _uiState.update { it.reducer().copy(successMessage = null, errorMessage = null) }
    }

    fun loadProperty(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            when (val result = repository.getPropertyDetail(id)) {
                is Resource.Success -> {
                    val p = result.data
                    _uiState.update {
                        it.copy(
                            id = p.id,
                            title = p.title,
                            description = p.description,
                            city = p.city,
                            location = p.location,
                            price = p.price,
                            bedrooms = p.bedrooms,
                            bathrooms = p.bathrooms,
                            area = p.area,
                            type = p.type,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                is Resource.Empty -> _uiState.update { it.copy(isLoading = false, errorMessage = "Propiedad no encontrada") }
                Resource.Loading -> Unit
            }
        }
    }

    fun create() = save(isCreate = true)

    fun update() = save(isCreate = false)

    private fun save(isCreate: Boolean) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            val snapshot = _uiState.value
            val property = snapshot.toDomain()
            val result = if (isCreate) repository.createProperty(property) else repository.updateProperty(snapshot.id ?: 0, property)
            when (result) {
                is Resource.Success -> _uiState.update {
                    it.copy(isLoading = false, successMessage = if (isCreate) "Propiedad creada" else "Propiedad actualizada")
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                is Resource.Empty -> _uiState.update { it.copy(isLoading = false, errorMessage = "Respuesta vacía del servidor") }
                Resource.Loading -> Unit
            }
        }
    }

    private fun PropertyFormUiState.toDomain() = Property(
        id = id ?: 0,
        title = title,
        description = description,
        city = city,
        location = location,
        price = price,
        area = area,
        bedrooms = bedrooms,
        bathrooms = bathrooms,
        status = "available",
        type = type,
        viewsCount = 0
    )

    class Factory(private val repository: PropertiesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = PropertyFormViewModel(repository) as T
    }
}
