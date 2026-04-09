package com.example.android_compose.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Fixed: Added '=' and ensured the type matches your filename
    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}