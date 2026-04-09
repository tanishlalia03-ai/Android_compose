package com.example.android_compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_compose.model.Category
import com.example.android_compose.model.RetrofitClient // Added import
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    var categoryListResponse: List<Category> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)
    var errorMessage: String by mutableStateOf("")

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                isLoading = true
                // UNCOMMENTED AND UPDATED:
                // Using .getcategories() to match your ApiInterface exactly
                val response = RetrofitClient.apiService.getcategories()
                categoryListResponse = response
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Unknown Error"
            } finally {
                // This MUST be false to hide the spinner and show the list
                isLoading = false
            }
        }
    }
}