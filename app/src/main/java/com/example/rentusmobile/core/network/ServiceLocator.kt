package com.example.rentusmobile.core.network

import com.example.rentusmobile.data.remote.PropertiesApi
import com.example.rentusmobile.data.repository.PropertiesRepositoryImpl
import com.example.rentusmobile.domain.repository.PropertiesRepository

object ServiceLocator {
    private val propertiesApi: PropertiesApi by lazy {
        NetworkModule.retrofit.create(PropertiesApi::class.java)
    }

    val propertiesRepository: PropertiesRepository by lazy {
        PropertiesRepositoryImpl(propertiesApi)
    }
}
