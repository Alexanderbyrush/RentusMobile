package com.example.rentusmobile.data.models

import com.google.gson.annotations.SerializedName

data class Property(
    val id: Int,
    val title: String,
    val city: String? = null,
    val address: String? = null,
    @SerializedName("monthly_price")
    val monthlyPrice: Double? = null,
    @SerializedName("area_m2")
    val areaM2: Double? = null,
    @SerializedName("num_bedrooms")
    val numBedrooms: Int? = null,
    @SerializedName("num_bathrooms")
    val numBathrooms: Int? = null,
    val status: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    val description: String? = null
)

data class PropertyListResponse(
    val data: List<Property> = emptyList()
)

data class PropertyDetailResponse(
    val data: Property
)
