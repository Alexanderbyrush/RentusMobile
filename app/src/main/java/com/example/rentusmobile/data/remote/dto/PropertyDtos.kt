package com.example.rentusmobile.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PropertyDto(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val city: String? = null,
    val location: String? = null,
    val price: Double? = null,
    @SerializedName("price_per_month") val pricePerMonth: Double? = null,
    val area: Double? = null,
    val bedrooms: Int? = null,
    val bathrooms: Int? = null,
    val status: String? = null,
    val type: String? = null,
    @SerializedName("views_count") val viewsCount: Int? = null,
)

data class PropertyCreateRequestDto(
    val title: String,
    val description: String,
    val city: String,
    val location: String,
    val price: Double,
    val bedrooms: Int,
    val bathrooms: Int,
    val area: Double,
    val type: String = "apartment"
)

data class PropertyUpdateRequestDto(
    val title: String,
    val description: String,
    val city: String,
    val location: String,
    val price: Double,
    val bedrooms: Int,
    val bathrooms: Int,
    val area: Double,
    val type: String
)
