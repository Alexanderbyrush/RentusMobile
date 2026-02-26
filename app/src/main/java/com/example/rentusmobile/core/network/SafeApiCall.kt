package com.example.rentusmobile.core.network

import com.example.rentusmobile.core.common.Resource

suspend fun <T> safeApiCall(block: suspend () -> ApiEnvelope<T>): Resource<T> {
    return try {
        val response = block()
        val data = response.data
        when {
            data == null -> Resource.Empty
            response.success == false -> Resource.Error(response.message ?: "La operación no fue exitosa")
            else -> Resource.Success(data = data, message = response.message)
        }
    } catch (t: Throwable) {
        Resource.Error(ErrorMapper.toMessage(t))
    }
}
