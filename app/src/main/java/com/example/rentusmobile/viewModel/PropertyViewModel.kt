package com.example.rentusmobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentusmobile.data.models.Property
import com.example.rentusmobile.data.repository.PropertyRepository
import com.example.rentusmobile.utils.Resource
import kotlinx.coroutines.launch

class PropertyViewModel : ViewModel() {

    private val repository: PropertyRepository = PropertyRepository()

    var propertiesState by mutableStateOf<Resource<List<Property>>>(Resource.Loading)
        private set

    var propertyDetailState by mutableStateOf<Resource<Property>>(Resource.Loading)
        private set

    fun loadProperties(city: String? = null) {
        viewModelScope.launch {
            propertiesState = Resource.Loading
            propertiesState = repository.getProperties(city)
        }
    }

    fun loadPropertyDetail(id: Int) {
        viewModelScope.launch {
            propertyDetailState = Resource.Loading
            propertyDetailState = repository.getPropertyById(id)
        }
    }
}
