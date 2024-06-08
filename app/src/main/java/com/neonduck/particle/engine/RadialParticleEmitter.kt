package com.neonduck.particle.engine

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.neonduck.particle.engine.extensions.nextFloat
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RadialParticleEmitter(
    private val numberOfParticles: Int = 1000,
) {

  private val particles = mutableListOf<Particle>()

  suspend fun load() =
      withContext(Dispatchers.Default) {
        for (i in 1..numberOfParticles) {
          particles.add(
              Particle(
                  position = ParticlePositionCenter,
                  color = Color.Red,
                  angle = Random.nextFloat(45f, 135f),
                  speed = Random.nextFloat(2f, 20f),
                  radius = Random.nextFloat(1f, 20f),
                  alpha = Random.nextFloat(0.2f, 1f),
              ))
        }
      }

  fun render(context: DrawScope, dt: Float) {
    cleanUp()
    repopulate()
    for (particle in particles) {
      particle.draw(context, dt)
    }
  }

  private fun cleanUp() {
    particles.removeIf { !it.isActive() }
  }

  private fun repopulate() {
    for (i in 0..<(numberOfParticles - particles.size)) {
      particles.add(
          Particle(
              position = ParticlePositionCenter,
              color = Color(0xFFf54e42),
              angle = Random.nextFloat(0f, 360f),
              speed = Random.nextFloat(2f, 20f),
              radius = Random.nextFloat(1f, 20f),
              alpha = Random.nextFloat(0.2f, 1f),
          ))
    }
  }
}
