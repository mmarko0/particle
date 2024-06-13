package com.neonduck.particle.examples

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.neonduck.particle.extensions.nextFloat
import com.neonduck.particlelib.Particle
import com.neonduck.particlelib.ParticleEmitter
import com.neonduck.particlelib.ParticlePositionCenter
import kotlin.math.min
import kotlin.random.Random

class ExampleParticleEmitter(
    private val numberOfParticles: Int = 1000,
) : ParticleEmitter {

  private val particles = mutableListOf<Particle>()

  override fun render(context: DrawScope, dt: Float) {
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
    for (i in 0..<min(5, (numberOfParticles - particles.size))) {
      particles.add(
          Particle(
              position = ParticlePositionCenter,
              color = Color(Random.nextInt(0xFF111111.toInt(), 0xFFFFFFFF.toInt())),
              angle = Random.nextFloat(0f, 360f),
              speed = 1f,
              maxRadius = Random.nextFloat(1f, 10f),
              maxAlpha = Random.nextFloat(0.2f, 1f),
              maxLifetime = Random.nextFloat(100f, 1000f),
              decreaseSize = true,
              fadeOut = true,
          ))
    }
  }
}
