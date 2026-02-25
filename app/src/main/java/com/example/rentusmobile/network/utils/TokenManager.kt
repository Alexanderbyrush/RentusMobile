package com.example.rentusmobile.network.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val TOKEN_DATASTORE_NAME = "token_preferences"
private val Context.tokenDataStore by preferencesDataStore(name = TOKEN_DATASTORE_NAME)

class TokenManager(private val context: Context) {

    suspend fun saveToken(token: String, refreshToken: String?, userId: Int?, expiresInSeconds: Long?) {
        val expiresAt = expiresInSeconds?.let { System.currentTimeMillis() + (it * 1000) }
        context.tokenDataStore.edit { pref ->
            pref[TOKEN_KEY] = token
            refreshToken?.let { pref[REFRESH_TOKEN_KEY] = it }
            userId?.let { pref[USER_ID_KEY] = it.toString() }
            expiresAt?.let { pref[EXPIRES_AT_KEY] = it }
        }
    }

    suspend fun getToken(): String? = context.tokenDataStore.data.map { it[TOKEN_KEY] }.first()

    suspend fun getRefreshToken(): String? = context.tokenDataStore.data.map { it[REFRESH_TOKEN_KEY] }.first()

    suspend fun isTokenValid(): Boolean {
        val token = getToken() ?: return false
        val expiresAt = context.tokenDataStore.data.map { it[EXPIRES_AT_KEY] }.first()
        return token.isNotBlank() && (expiresAt == null || expiresAt > System.currentTimeMillis())
    }

    suspend fun clearToken() {
        context.tokenDataStore.edit { pref ->
            pref.remove(TOKEN_KEY)
            pref.remove(REFRESH_TOKEN_KEY)
            pref.remove(USER_ID_KEY)
            pref.remove(EXPIRES_AT_KEY)
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val EXPIRES_AT_KEY = longPreferencesKey("expires_at")
    }
}
