package com.neonduck.particle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.neonduck.particle.engine.ParticleScreen
import com.neonduck.particle.ui.theme.ParticleShapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParticleShapeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ParticleScreen()
                }
            }
        }
    }
}