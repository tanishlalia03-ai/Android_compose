package com.example.android_compose.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_compose.model.RetrofitClient
import com.example.android_compose.model.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow<UserResponse?>(null)
    val userProfile: StateFlow<UserResponse?> = _userProfile

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchProfile(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Calling your ApiService through your RetrofitClient
                val response = RetrofitClient.apiService.getUserProfile("Bearer $token")
                if (response.isSuccessful) {
                    _userProfile.value = response.body()
                }
            } catch (e: Exception) {
                // Handle network errors
            } finally {
                _isLoading.value = false
            }
        }
    }
}