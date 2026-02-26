package com.example.rentusmobile.presentation.properties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rentusmobile.core.common.Resource
import com.example.rentusmobile.domain.repository.PropertiesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PropertiesViewModel(
    private val repository: PropertiesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PropertiesUiState(isLoading = true))
    val uiState: StateFlow<PropertiesUiState> = _uiState.asStateFlow()

    init {
        loadProperties()
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun loadProperties() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, isEmpty = false) }
            when (val result = repository.getProperties()) {
                is Resource.Success -> _uiState.update { state ->
                    val filtered = filter(result.data, state.query)
                    state.copy(isLoading = false, data = filtered, isEmpty = filtered.isEmpty())
                }
                is Resource.Empty -> _uiState.update { it.copy(isLoading = false, data = emptyList(), isEmpty = true) }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                Resource.Loading -> Unit
            }
        }
    }

    private fun filter(properties: List<com.example.rentusmobile.domain.model.Property>, query: String): List<com.example.rentusmobile.domain.model.Property> {
        if (query.isBlank()) return properties
        return properties.filter {
            it.title.contains(query, true) || it.city.contains(query, true) || it.type.contains(query, true)
        }
    }

    class Factory(private val repository: PropertiesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = PropertiesViewModel(repository) as T
    }
}
