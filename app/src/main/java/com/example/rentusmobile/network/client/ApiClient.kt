package com.example.rentusmobile.network.client

import android.content.Context
import com.example.rentusmobile.network.api.AuthApi
import com.example.rentusmobile.network.api.PropertyApi
import com.example.rentusmobile.network.interceptor.AuthInterceptor
import com.example.rentusmobile.network.interceptor.TokenRefreshInterceptor
import com.example.rentusmobile.network.utils.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://backend-rentus-production.up.railway.app/api/"

    @Volatile
    private var initialized = false
    private lateinit var tokenManager: TokenManager
    private lateinit var retrofitWithoutRefresh: Retrofit
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        if (initialized) return

        synchronized(this) {
            if (initialized) return

            tokenManager = TokenManager(context.applicationContext)

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val baseClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(AuthInterceptor(tokenManager))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            retrofitWithoutRefresh = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(baseClient)
                .build()

            val refreshClient = baseClient.newBuilder()
                .addInterceptor(TokenRefreshInterceptor(tokenManager, retrofitWithoutRefresh))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(refreshClient)
                .build()

            initialized = true
        }
    }

    fun tokenManager(): TokenManager {
        check(initialized) { "ApiClient.init(context) debe ejecutarse antes de usar ApiClient." }
        return tokenManager
    }

    fun authApi(): AuthApi {
        check(initialized) { "ApiClient.init(context) debe ejecutarse antes de usar ApiClient." }
        return retrofit.create(AuthApi::class.java)
    }

    fun propertyApi(): PropertyApi {
        check(initialized) { "ApiClient.init(context) debe ejecutarse antes de usar ApiClient." }
        return retrofit.create(PropertyApi::class.java)
    }
}
