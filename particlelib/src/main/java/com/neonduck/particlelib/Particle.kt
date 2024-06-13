package com.neonduck.particlelib

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.neonduck.particlelib.extensions.toRadians
import kotlin.math.cos
import kotlin.math.sin

data class Particle(
    private val position: ParticlePosition,
    private val angle: Float = 90f,
    private val speed: Float = 10f,
    private val maxRadius: Float = 5f,
    private val maxLifetime: Float = 1000f,
    private val color: Color = Color.Black,
    private val maxAlpha: Float = 1f,
    private val decreaseSize: Boolean = false,
    private val fadeOut: Boolean = false,
) {

  private val velocity: Offset =
      Offset(
          x = speed * cos(angle.toRadians()),
          y = -speed * sin(angle.toRadians()),
      )

  private var lifetime = maxLifetime
  private var radius = maxRadius
  private var alpha = maxAlpha

  private var x = 0f
  private var y = 0f
  private var calculatedPosition = false

  fun draw(
      context: DrawScope,
      dt: Float,
  ) {
    if (!calculatedPosition) {
      calculatePosition(context)
    }

    lifetime -= dt

    if (lifetime <= 0) {
      return
    }

    val ageRatio = lifetime / maxLifetime
    if (decreaseSize) {
      radius = maxRadius * ageRatio
    }
    if (fadeOut) {
      alpha = ageRatio
    }

    x += velocity.x * dt
    y += velocity.y * dt

    context.drawCircle(
        brush = SolidColor(color.copy(alpha = alpha)), radius = radius, center = Offset(x, y))
  }

  fun isActive() = lifetime > 0

  private fun calculatePosition(context: DrawScope) {
    when (position) {
      is ParticlePositionAbsolute -> {
        x = position.x
        y = position.y
      }
      is ParticlePositionStart -> {
        x = 0f
        y = 0f
      }
      is ParticlePositionCenter -> {
        x = context.center.x
        y = context.center.y
      }
    }
    calculatedPosition = true
  }
}
