package com.example.android_compose.JetpackCompose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Firstscreen() {
    val context = LocalContext.current


    var nameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Simple Text showing "Hello"
        Text(text = "Hello")

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Text (TextField)
        TextField(
            value = nameText,
            onValueChange = { nameText = it },
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button that shows the state variable in the Toast
        Button(onClick = {
            if (nameText.isNotEmpty()) {
                Toast.makeText(context, "Hello $nameText", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a name first!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Show name")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "hello guys whats up")

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = emailText,
            onValueChange = {emailText=it},
            label={Text("enter your email")}
        )

        Button(onClick = {
            if (emailText.isNotEmpty()) {
                Toast.makeText(context, "Hello $emailText", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a name first!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text="show email")
        }


        Spacer(modifier=Modifier.height(30.dp))

        // --- THE ROW STARTS HERE ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // Space between items
            verticalAlignment = Alignment.CenterVertically   // Center items up/down
        ) {
            Button(onClick = {
                Toast.makeText(context, "Clicked Left", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Left Button")
            }

            Button(onClick = {
                Toast.makeText(context, "Clicked Right", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Right Button")
            }
        }


    }

}



@Preview(showBackground = true)
@Composable
fun FirstscreenPreview() {
    //Firstscreen()
    //DayTwoScreen()
    AssignmentScreen()
}

data class Task(val id: String, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayTwoScreen() {
    val tasks = List(20) { i ->
        Task("$i", "This is process for $i")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("This is top bar ") },)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Action here */ }
            ) {
                Icon(Icons.Default.Email, contentDescription = "email")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text("Hello guys", modifier = Modifier.padding(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(tasks.size) { index ->
                    listItem(tasks[index])
                }
            }
        }
    }
}


@Composable
fun listItem(task: Task) {
    Column {
        Text(task.id)
        Text(task.name)

        Divider()
    }
}



@Composable
fun AssignmentScreen() {
    // A list of 20 items to demonstrate the scrolling
    val itemsList = List(20) { index -> "Hello ${index + 1}" }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEADDFF)) // Light purple background for the whole list
    ) {
        itemsIndexed(itemsList) { index, itemText ->
            PurpleListItem(text = itemText)
        }
    }
}

@Composable
fun PurpleListItem(text: String) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6750A4) // Deep Purple background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // CHANGED: Added colors parameter here
            Button(
                onClick = {
                    Toast.makeText(context, "More info for $text", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,      // Background of the button
                    contentColor = Color.White       // Color of the text inside the button
                )
            ) {
                Text(text = "More")
            }
        }
    }
}