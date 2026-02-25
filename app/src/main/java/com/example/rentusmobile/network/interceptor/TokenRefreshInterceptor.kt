package com.example.rentusmobile.network.interceptor

import com.example.rentusmobile.network.api.AuthApi
import com.example.rentusmobile.network.dto.RefreshTokenRequest
import com.example.rentusmobile.network.utils.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit

class TokenRefreshInterceptor(
    private val tokenManager: TokenManager,
    private val retrofit: Retrofit
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)

        if (response.code != 401 || originalRequest.url.encodedPath.contains("/auth/refresh")) {
            return response
        }

        response.close()
        val refreshed = runBlocking { refreshToken() }
        if (!refreshed) return chain.proceed(originalRequest)

        val newToken = runBlocking { tokenManager.getToken() }
        val retriedRequest = originalRequest.newBuilder().apply {
            header("Authorization", "Bearer $newToken")
            header("Accept", "application/json")
            header("Content-Type", "application/json")
        }.build()

        return chain.proceed(retriedRequest)
    }

    private suspend fun refreshToken(): Boolean {
        val refreshToken = tokenManager.getRefreshToken() ?: return false
        return try {
            val authApi = retrofit.create(AuthApi::class.java)
            val refreshResponse = authApi.refresh(RefreshTokenRequest(refreshToken))
            if (refreshResponse.isSuccessful) {
                refreshResponse.body()?.let { auth ->
                    tokenManager.saveToken(auth.token, auth.refreshToken, auth.user.id, auth.expiresIn)
                }
                true
            } else {
                tokenManager.clearToken()
                false
            }
        } catch (_: Exception) {
            false
        }
    }
}
