package com.example.rentusmobile.presentation.properties

import com.example.rentusmobile.domain.model.Property

data class PropertiesUiState(
    val isLoading: Boolean = false,
    val data: List<Property> = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false,
    val query: String = ""
)

data class PropertyDetailUiState(
    val isLoading: Boolean = false,
    val data: Property? = null,
    val error: String? = null,
    val isEmpty: Boolean = false
)

data class PropertyFormUiState(
    val id: Int? = null,
    val title: String = "",
    val description: String = "",
    val city: String = "",
    val location: String = "",
    val price: String = "",
    val bedrooms: String = "",
    val bathrooms: String = "",
    val area: String = "",
    val type: String = "apartment",
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
