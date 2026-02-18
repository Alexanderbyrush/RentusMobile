package com.example.rentusmobile.data.models

data class User(
    val id: String,
    val email: String,
    val name: String,
    val phone: String? = null,
    val photoUrl: String? = null
)

data class RegisterData(
    val name: String,
    val email: String,
    val phone: String,
    val idDocument: String,
    val address: String,
    val password: String
)
