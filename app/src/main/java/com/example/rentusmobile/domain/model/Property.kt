package com.example.rentusmobile.domain.model

data class Property(
    val id: Int,
    val title: String,
    val description: String,
    val city: String,
    val location: String,
    val price: String,
    val area: String,
    val bedrooms: String,
    val bathrooms: String,
    val status: String,
    val type: String,
    val viewsCount: Int
)
