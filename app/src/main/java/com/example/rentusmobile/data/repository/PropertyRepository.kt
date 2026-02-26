package com.example.rentusmobile.data.repository

import com.example.rentusmobile.data.models.Property
import com.example.rentusmobile.network.client.ApiClient
import com.example.rentusmobile.utils.Resource

class PropertyRepository {

    private val api = ApiClient.propertyApi()

    suspend fun getProperties(city: String? = null): Resource<List<Property>> {
        return runCatching {
            api.getProperties(city = city)
        }.fold(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    Resource.Success(response.body()?.data.orEmpty())
                } else {
                    Resource.Error("No se pudieron cargar las propiedades", response.code())
                }
            },
            onFailure = {
                Resource.Error(it.message ?: "Error de red al obtener propiedades")
            }
        )
    }

    suspend fun getPropertyById(id: Int): Resource<Property> {
        return runCatching {
            api.getPropertyById(id)
        }.fold(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    response.body()?.data?.let { property ->
                        Resource.Success(property)
                    } ?: Resource.Error("No se encontró la propiedad", response.code())
                } else {
                    Resource.Error("No se pudo cargar el detalle", response.code())
                }
            },
            onFailure = {
                Resource.Error(it.message ?: "Error de red al obtener detalle")
            }
        )
    }
}
