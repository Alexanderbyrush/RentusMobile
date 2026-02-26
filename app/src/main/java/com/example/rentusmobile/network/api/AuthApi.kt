package com.example.rentusmobile.network.api

import com.example.rentusmobile.data.models.AuthResponse
import com.example.rentusmobile.data.models.LoginRequest
import com.example.rentusmobile.data.models.RegisterData
import com.example.rentusmobile.network.dto.RefreshTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterData): Response<AuthResponse>

    @POST("auth/refresh")
    suspend fun refresh(@Body request: RefreshTokenRequest): Response<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    @GET("auth/me")
    suspend fun me(): Response<AuthResponse>
}
