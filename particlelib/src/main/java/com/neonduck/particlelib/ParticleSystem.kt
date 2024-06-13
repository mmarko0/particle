package com.neonduck.particlelib

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ParticleSystem(modifier: Modifier = Modifier, particleEmitter: ParticleEmitter) {
  var dt by remember { mutableFloatStateOf(0f) }
  var previousTime by remember { mutableLongStateOf(System.nanoTime()) }

  LaunchedEffect(Unit) {
    withContext(Dispatchers.Default) {
      while (true) {
        withFrameNanos {
          dt = ((it - previousTime) / 1E7).toFloat()
          previousTime = it
        }
      }
    }
  }

  Box(modifier = modifier.fillMaxSize()) {
    Box(
        modifier =
            Modifier.align(Alignment.Center).fillMaxSize().background(Color.Black).drawBehind {
              particleEmitter.render(this, dt)
            })
  }
}
