package com.neonduck.particlelib

import androidx.compose.ui.graphics.drawscope.DrawScope

interface ParticleEmitter {
  fun render(context: DrawScope, dt: Float)
}
