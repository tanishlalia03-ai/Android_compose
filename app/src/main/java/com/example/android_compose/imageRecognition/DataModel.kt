package com.example.android_compose.imageRecognition

data class SightengineResponse(
    val status: String,
    val type: TypeData?
)

data class TypeData(
    val ai_generated: Double // Value between 0 and 1
)