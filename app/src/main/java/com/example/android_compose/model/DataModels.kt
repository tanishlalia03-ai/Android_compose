package com.example.android_compose.model

import com.google.gson.annotations.SerializedName

data class Category(
    val id: Int,
    val name: String,
    val image: String
)

data class LoginResponse(
    val access_token: String ?= null,
    val refresh_token: String ?= null
)


data class LoginRequest(
    val email: String ?= null,
    val password: String ?= null
)

data class UserRequest (
    val name: String ?= null,
    val email: String ?= null,
    val password: String ?= null,
    val avatar: String ?= null
)

data class UserResponse(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val role: String? = null,
    val message: String? = null
)

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val category: Category,
    val images: List<String>
)

data class ProductResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("category")
    val category: Category
)

data class UserProfile(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("avatar")
    val avatar: String
)

data class StatusResponse(
    val message: String,
    val statusCode: Int? = null
)