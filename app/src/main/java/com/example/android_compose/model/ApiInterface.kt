package com.example.android_compose.model

import retrofit2.http.GET

interface ApiInterface {
    @GET("categories")
    suspend fun getcategories(): List<Category>
}