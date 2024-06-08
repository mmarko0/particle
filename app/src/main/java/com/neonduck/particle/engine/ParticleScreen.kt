package com.neonduck.particle.engine

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
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ParticleScreen(modifier: Modifier = Modifier) {
  val exampleParticleEmitter = remember { ExampleParticleEmitter() }
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
              exampleParticleEmitter.render(this, dt)
            })
  }
}

@Preview
@Composable
fun ParticleCirclePreview() {
  ParticleScreen()
}
