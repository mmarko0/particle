package com.neonduck.particle.engine

import androidx.compose.ui.graphics.drawscope.DrawScope

interface ParticleEmitter {
  fun render(context: DrawScope, dt: Float)
}
