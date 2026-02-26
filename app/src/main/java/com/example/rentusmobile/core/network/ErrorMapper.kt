package com.example.rentusmobile.core.network

import retrofit2.HttpException
import java.io.IOException

object ErrorMapper {
    fun toMessage(throwable: Throwable): String {
        return when (throwable) {
            is IOException -> "Sin conexión. Revisa tu internet e inténtalo nuevamente."
            is HttpException -> {
                val body = throwable.response()?.errorBody()?.string().orEmpty()
                if (body.isNotBlank()) "Error ${throwable.code()}: $body" else "Error HTTP ${throwable.code()}"
            }
            else -> throwable.message ?: "Error desconocido"
        }
    }
}
