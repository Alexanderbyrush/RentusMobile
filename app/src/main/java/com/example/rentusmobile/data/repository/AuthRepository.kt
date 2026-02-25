package com.example.rentusmobile.data.repository

import com.example.rentusmobile.data.models.AuthResponse
import com.example.rentusmobile.data.models.ErrorResponse
import com.example.rentusmobile.data.models.LoginRequest
import com.example.rentusmobile.data.models.RegisterData
import com.example.rentusmobile.data.models.User
import com.example.rentusmobile.network.client.ApiClient
import com.example.rentusmobile.utils.Resource
import com.google.gson.Gson

class AuthRepository {

    private val api = ApiClient.authApi()
    private val tokenManager = ApiClient.tokenManager()

    suspend fun login(email: String, password: String, rememberMe: Boolean): Resource<User> {
        return runCatching {
            api.login(LoginRequest(email = email, password = password, rememberMe = rememberMe))
        }.fold(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        persistSession(body)
                        Resource.Success(body.user, body.message)
                    } else {
                        Resource.Error("Respuesta vacía del servidor", response.code())
                    }
                } else {
                    parseError(response.code(), response.errorBody()?.string())
                }
            },
            onFailure = {
                Resource.Error(it.message ?: "No fue posible conectar con el servidor")
            }
        )
    }

    suspend fun register(userData: RegisterData): Resource<User> {
        return runCatching {
            api.register(userData)
        }.fold(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        persistSession(body)
                        Resource.Success(body.user, body.message)
                    } else {
                        Resource.Error("Respuesta vacía del servidor", response.code())
                    }
                } else {
                    parseError(response.code(), response.errorBody()?.string())
                }
            },
            onFailure = {
                Resource.Error(it.message ?: "No fue posible conectar con el servidor")
            }
        )
    }

    suspend fun logout() {
        runCatching { api.logout() }
        tokenManager.clearToken()
    }

    suspend fun hasValidSession(): Boolean = tokenManager.isTokenValid()

    private suspend fun persistSession(authResponse: AuthResponse) {
        tokenManager.saveToken(
            token = authResponse.token,
            refreshToken = authResponse.refreshToken,
            userId = authResponse.user.id,
            expiresInSeconds = authResponse.expiresIn
        )
    }

    private fun parseError(code: Int, errorBody: String?): Resource.Error {
        return runCatching {
            val error = Gson().fromJson(errorBody, ErrorResponse::class.java)
            Resource.Error(
                message = error?.message ?: "Error desconocido",
                code = code,
                errors = error?.errors
            )
        }.getOrElse {
            Resource.Error("Error HTTP $code", code = code)
        }
    }
}
