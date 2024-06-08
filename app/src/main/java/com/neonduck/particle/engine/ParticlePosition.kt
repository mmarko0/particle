package com.neonduck.particle.engine

sealed class ParticlePosition

data class ParticlePositionAbsolute(
    val x: Float,
    val y: Float,
): ParticlePosition()

data object ParticlePositionCenter : ParticlePosition()

data object ParticlePositionStart : ParticlePosition()