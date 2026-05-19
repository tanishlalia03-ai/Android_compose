package com.example.android_compose.topic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("K O J O", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 4.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title and Edit Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Review", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(20.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Cart Items
        CartItemRow("Tart Jar", "£ 10.90", "+2 Extras")
        Spacer(modifier = Modifier.height(24.dp))
        CartItemRow("Almond & Banana Toast", "£ 12.50", "No Extras")

        Spacer(modifier = Modifier.height(70.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "£ 23.40",
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            // Circular Arrow Button
            Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 4.dp,
                modifier = Modifier.size(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // "You might also" Section
        Text("You might also love", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(3) {
                SuggestionCard()
            }
        }
    }
}

@Composable
fun CartItemRow(name: String, price: String, subtitle: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(subtitle, color = Color.LightGray, fontSize = 12.sp)
        }
        Text(price, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        // Placeholders for food item small images
        repeat(2) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp)))
            }
        }
    }
}

@Composable
fun SuggestionCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.size(90.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            // Placeholder for small suggestion image
            Box(modifier = Modifier.size(50.dp).background(Color(0xFFF5F5F5), CircleShape))
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ReviewPreview() {
    ReviewScreen()
}