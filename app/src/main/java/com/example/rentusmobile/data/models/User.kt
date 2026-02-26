package com.example.rentusmobile.data.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    @SerializedName("id_documento")
    val idDocumento: String? = null,
    val status: String,
    @SerializedName("verification_status")
    val verificationStatus: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String? = null,
    val role: String,
    val photo: String? = null,
    val bio: String? = null,
    val department: String? = null,
    val city: String? = null,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class RegisterData(
    val name: String,
    val email: String,
    val phone: String,
    @SerializedName("id_documento")
    val idDocument: String,
    val address: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String,
    @SerializedName("remember")
    val rememberMe: Boolean
)

data class AuthResponse(
    val message: String,
    val user: User,
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String? = null,
    @SerializedName("expires_in")
    val expiresIn: Long? = null
)

data class ErrorResponse(
    val message: String? = null,
    val errors: Map<String, List<String>>? = null
)
