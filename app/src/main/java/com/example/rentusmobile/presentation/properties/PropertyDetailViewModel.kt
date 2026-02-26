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

class PropertyDetailViewModel(
    private val repository: PropertiesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PropertyDetailUiState(isLoading = true))
    val uiState: StateFlow<PropertyDetailUiState> = _uiState.asStateFlow()

    fun load(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = repository.getPropertyDetail(id)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, data = result.data) }
                    repository.incrementPropertyViews(id)
                }
                is Resource.Empty -> _uiState.update { it.copy(isLoading = false, isEmpty = true) }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                Resource.Loading -> Unit
            }
        }
    }

    class Factory(private val repository: PropertiesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = PropertyDetailViewModel(repository) as T
    }
}
