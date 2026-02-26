package com.example.rentusmobile.domain.repository

import com.example.rentusmobile.core.common.Resource
import com.example.rentusmobile.domain.model.Property

interface PropertiesRepository {
    suspend fun getProperties(): Resource<List<Property>>
    suspend fun getPropertyDetail(id: Int): Resource<Property>
    suspend fun createProperty(property: Property): Resource<Property>
    suspend fun updateProperty(id: Int, property: Property): Resource<Property>
    suspend fun deleteProperty(id: Int): Resource<Unit>
    suspend fun incrementPropertyViews(id: Int): Resource<Unit>
}
