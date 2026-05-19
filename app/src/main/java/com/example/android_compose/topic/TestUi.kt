package com.example.android_compose.topic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SoftGreen = Color(0xFFCDE7BE)
val SoftWhite = Color(0xFFF9F9F9)
val DarkText = Color(0xFF1A1A1A)

@Composable
fun KojoFoodApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftWhite)
            .padding(24.dp) // Increased padding for a cleaner look
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(24.dp))

        Text("Breakfast", style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold))
        Text("Until 12pm", color = Color(0xFFE57373), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(24.dp))
        CategoryRow()

        Spacer(modifier = Modifier.height(32.dp))
        GrabAndGoSection()
        GrabAndGoSection2()
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "K O J O",
            style = TextStyle(letterSpacing = 4.sp, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        )
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun CategoryRow() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        item { CategoryItem("Tart", isSelected = true) }
        item { CategoryItem("Fruit", isSelected = false) }
        item { CategoryItem("Toast", isSelected = false) }
    }
}

@Composable
fun CategoryItem(name: String, isSelected: Boolean) {
    Card(
        shape = RoundedCornerShape(32.dp), // Higher radius for that soft look
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) SoftGreen else Color.White
        ),
        modifier = Modifier
            .width(110.dp)
            .height(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(12.dp)
        ) {
            // Placeholder for Food Image
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = name, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))

            if (isSelected) {
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun GrabAndGoSection() {
    Column {
        Text("Grab & Go", style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Fruit Bowl", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("1700kj / 430kcal", color = Color.LightGray, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("£ 10.90", fontWeight = FontWeight.ExtraBold)
                }
                // Placeholder for Item Image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@Composable
fun GrabAndGoSection2() {
    Column {
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Accai Na Tigeld", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("800kj / 210kcal", color = Color.LightGray, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("£ 8.90", fontWeight = FontWeight.ExtraBold)
                }
                // Placeholder for Item Image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
                )
            }
        }
    }
}




@Preview(showBackground = true, device = "spec:width=411dp,height=891dp", name = "Kojo App Preview")
@Composable
fun KojoAppPreview() {
    MaterialTheme {
        KojoFoodApp()
    }
}