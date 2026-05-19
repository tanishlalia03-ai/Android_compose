package com.o7solutions.android_compose.BluetoothHid

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun SpeechToTextScreen() {
    val context = LocalContext.current

    // States to hold the text and the listening status
    var textResult by remember { mutableStateOf("Press the button and start speaking...") }
    var isListening by remember { mutableStateOf(false) }

    // --- Part 1: Text-to-Speech (TTS) Setup ---
    val tts = remember {
        var ttsInstance: TextToSpeech? = null
        ttsInstance = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                ttsInstance?.language = Locale.US
            }
        }
        ttsInstance
    }

    // --- Part 2: Speech-to-Text (STT) Setup ---
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }

    val recognizerIntent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }
    }

    val listener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) { isListening = true }
        override fun onBeginningOfSpeech() { textResult = "Listening..." }
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}
        override fun onEndOfSpeech() { isListening = false }
        override fun onError(error: Int) {
            isListening = false
            textResult = "Error occurred. Please try again."
        }
        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                textResult = matches[0]
            }
            isListening = false
        }
        override fun onPartialResults(partialResults: Bundle?) {}
        override fun onEvent(eventType: Int, params: Bundle?) {}
    }

    // Attach listener
    DisposableEffect(Unit) {
        speechRecognizer.setRecognitionListener(listener)
        onDispose {
            speechRecognizer.destroy()
            tts?.stop()
            tts?.shutdown()
        }
    }

    // --- Part 3: Permission Handling ---
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            speechRecognizer.startListening(recognizerIntent)
        }
    }

    // --- Part 4: UI Layout ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Voice Assistant (SST & TTS)",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Output Box
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = textResult,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Speech-to-Text Button
        Button(
            onClick = {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isListening) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isListening) "Mic is Recording..." else "Audio to Text (SST)")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Text-to-Speech Button
        Button(
            onClick = {
                tts?.speak(textResult, TextToSpeech.QUEUE_FLUSH, null, null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = textResult.isNotEmpty() && !isListening
        ) {
            Text("Text to Audio (TTS)")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Clear Button
        TextButton(onClick = { textResult = "Press the button and start speaking..." }) {
            Text("Clear Text")
        }
    }
}