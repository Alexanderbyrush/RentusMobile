package com.example.rentusmobile.network.api

import com.example.rentusmobile.data.models.PropertyDetailResponse
import com.example.rentusmobile.data.models.PropertyListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PropertyApi {
    @GET("properties")
    suspend fun getProperties(
        @Query("page") page: Int = 1,
        @Query("city") city: String? = null
    ): Response<PropertyListResponse>

    @GET("properties/{id}")
    suspend fun getPropertyById(
        @Path("id") id: Int
    ): Response<PropertyDetailResponse>
}
