package com.nenykely.jeux_video_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nenykely.jeux_video_api.ui.JeuScreen
import com.nenykely.jeux_video_api.ui.theme.Jeux_video_apiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jeux_video_apiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Utilisation de JeuScreen à la place de LivreScreen
                    androidx.compose.foundation.layout.Box(modifier = Modifier.padding(innerPadding)) {
                        JeuScreen()
                    }
                }
            }
        }
    }
}
