package com.example.rentusmobile.utils

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()

    data class Success<T>(
        val data: T,
        val message: String? = null
    ) : Resource<T>()

    data class Error(
        val message: String,
        val code: Int? = null,
        val errors: Map<String, List<String>>? = null
    ) : Resource<Nothing>()
}

fun Resource<*>.getValidationErrors(): Map<String, String>? {
    return (this as? Resource.Error)?.errors?.mapValues { (_, value) -> value.firstOrNull().orEmpty() }
}
