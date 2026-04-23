package com.example.android_compose.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_compose.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val user by viewModel.userProfile.collectAsState()
    val loading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (loading) {
            CircularProgressIndicator()
        } else if (user != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = user?.avatar,
                    contentDescription = "Avatar",
                    modifier = Modifier.size(100.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = user?.name ?: "", style = MaterialTheme.typography.headlineMedium)
                Text(text = user?.email ?: "", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            Text("Failed to load profile.")
        }
    }
}