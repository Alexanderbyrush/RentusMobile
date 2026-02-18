package com.example.rentusmobile.data.repository

import com.example.rentusmobile.data.models.RegisterData
import com.example.rentusmobile.data.models.User
import kotlinx.coroutines.delay

class AuthRepository {
    suspend fun login(email: String, password: String): Result<User> {
        delay(1000)
        return Result.success(User(id = "1", email = email, name = "Usuario Test"))
    }

    suspend fun register(userData: RegisterData): Result<User> {
        delay(1000)
        return Result.success(User(id = "1", email = userData.email, name = userData.name))
    }
}
