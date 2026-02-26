package com.example.rentusmobile.data.repository

import com.example.rentusmobile.core.common.Resource
import com.example.rentusmobile.core.network.safeApiCall
import com.example.rentusmobile.data.mapper.toCreateRequest
import com.example.rentusmobile.data.mapper.toDomain
import com.example.rentusmobile.data.mapper.toUpdateRequest
import com.example.rentusmobile.data.remote.PropertiesApi
import com.example.rentusmobile.domain.model.Property
import com.example.rentusmobile.domain.repository.PropertiesRepository

class PropertiesRepositoryImpl(
    private val api: PropertiesApi
) : PropertiesRepository {

    override suspend fun getProperties(): Resource<List<Property>> {
        return when (val result = safeApiCall { api.getProperties() }) {
            is Resource.Success -> {
                val mapped = result.data.map { it.toDomain() }
                if (mapped.isEmpty()) Resource.Empty else Resource.Success(mapped, result.message)
            }
            is Resource.Empty -> Resource.Empty
            is Resource.Error -> result
            Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun getPropertyDetail(id: Int): Resource<Property> {
        return when (val result = safeApiCall { api.getPropertyDetail(id) }) {
            is Resource.Success -> Resource.Success(result.data.toDomain(), result.message)
            is Resource.Empty -> Resource.Empty
            is Resource.Error -> result
            Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun createProperty(property: Property): Resource<Property> {
        return when (val result = safeApiCall { api.createProperty(property.toCreateRequest()) }) {
            is Resource.Success -> Resource.Success(result.data.toDomain(), result.message)
            is Resource.Empty -> Resource.Empty
            is Resource.Error -> result
            Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun updateProperty(id: Int, property: Property): Resource<Property> {
        return when (val result = safeApiCall { api.updateProperty(id, property.toUpdateRequest()) }) {
            is Resource.Success -> Resource.Success(result.data.toDomain(), result.message)
            is Resource.Empty -> Resource.Empty
            is Resource.Error -> result
            Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun deleteProperty(id: Int): Resource<Unit> {
        return when (val result = safeApiCall { api.deleteProperty(id) }) {
            is Resource.Success -> Resource.Success(Unit, result.message)
            is Resource.Empty -> Resource.Success(Unit)
            is Resource.Error -> result
            Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun incrementPropertyViews(id: Int): Resource<Unit> {
        return when (val result = safeApiCall { api.incrementViews(id) }) {
            is Resource.Success -> Resource.Success(Unit, result.message)
            is Resource.Empty -> Resource.Success(Unit)
            is Resource.Error -> result
            Resource.Loading -> Resource.Loading
        }
    }
}
