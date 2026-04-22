package com.example.android_compose.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_compose.model.LoginRequest
import com.example.android_compose.model.RetrofitClient
import com.example.android_compose.utils.DataStoreManager

import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // UI State variables
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Persistent storage manager
    private val dataStoreManager = DataStoreManager(application)

    fun login(onSuccess: (String, String) -> Unit) {
        // 1. Validation
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please enter both email and password"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                // Using the model you created earlier
                val loginRequest = LoginRequest(email = email, password = password)
                val response = RetrofitClient.apiService.login(loginRequest)

                if (response.isSuccessful) {
                    val body = response.body()

                    // Note: Ensure your LoginResponse model fields match these names
                    val access = body?.access_token
                    val refresh = body?.refresh_token

                    if (!access.isNullOrEmpty() && !refresh.isNullOrEmpty()) {
                        // 2. Save tokens to DataStore
                        dataStoreManager.saveTokens(access, refresh)

                        // 3. Success Callback
                        onSuccess(access, refresh)
                    } else {
                        errorMessage = "Invalid server response: Missing tokens."
                    }
                } else {
                    errorMessage = when(response.code()) {
                        401 -> "Invalid email or password"
                        404 -> "Service not found"
                        500 -> "Server error, try again later"
                        else -> "Error: ${response.code()}"
                    }
                }
            } catch (e: IOException) {
                errorMessage = "Connection failed. Check your internet."
            } catch (e: Exception) {
                errorMessage = "Unexpected error: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}