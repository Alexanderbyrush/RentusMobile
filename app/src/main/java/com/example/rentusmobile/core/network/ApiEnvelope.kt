package com.example.rentusmobile.core.network

import com.google.gson.JsonElement

data class ApiEnvelope<T>(
    val success: Boolean? = null,
    val message: String? = null,
    val data: T? = null,
    val meta: JsonElement? = null
)
