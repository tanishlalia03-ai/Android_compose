package com.example.android_compose.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_compose.model.RetrofitClient
import com.example.android_compose.model.UserRequest
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    // UI State variables for the Register Screen
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    /**
     * Calls the Create User API.
     * @param onSuccess Callback to navigate the user (usually back to Login).
     */
    fun registerUser(onSuccess: () -> Unit) {
        // 1. Basic Validation
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            errorMessage = "All fields are required"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                // 2. Prepare the Request (Using the model you created)
                val userRequest = UserRequest(
                    name = name,
                    email = email,
                    password = password,
                    avatar = "https://picsum.photos/800" // Required by Platzi API
                )

                // 3. Make the API Call
                val response = RetrofitClient.apiService.createUser(userRequest)

                if (response.isSuccessful) {
                    // 4. Trigger the success callback (Navigate to Login)
                    onSuccess()
                } else {
                    // Handle API errors (e.g., 400 if email is already taken)
                    errorMessage = when (response.code()) {
                        400 -> "Email already exists or invalid data"
                        else -> "Server error: ${response.code()}"
                    }
                }
            } catch (e: IOException) {
                errorMessage = "Network error. Please check your internet."
            } catch (e: Exception) {
                errorMessage = "Unexpected error: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}