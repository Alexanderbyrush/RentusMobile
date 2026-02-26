package com.example.rentusmobile.data.remote

import com.example.rentusmobile.core.network.ApiEnvelope
import com.example.rentusmobile.data.remote.dto.PropertyCreateRequestDto
import com.example.rentusmobile.data.remote.dto.PropertyDto
import com.example.rentusmobile.data.remote.dto.PropertyUpdateRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PropertiesApi {
    @GET("properties")
    suspend fun getProperties(): ApiEnvelope<List<PropertyDto>>

    @GET("properties/{id}")
    suspend fun getPropertyDetail(@Path("id") id: Int): ApiEnvelope<PropertyDto>

    @POST("properties")
    suspend fun createProperty(@Body body: PropertyCreateRequestDto): ApiEnvelope<PropertyDto>

    @PUT("properties/{id}")
    suspend fun updateProperty(@Path("id") id: Int, @Body body: PropertyUpdateRequestDto): ApiEnvelope<PropertyDto>

    @DELETE("properties/{id}")
    suspend fun deleteProperty(@Path("id") id: Int): ApiEnvelope<Any>

    @POST("properties/{id}/increment-views")
    suspend fun incrementViews(@Path("id") id: Int): ApiEnvelope<Any>
}
