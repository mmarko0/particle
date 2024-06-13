package com.neonduck.particlelib

sealed class ParticlePosition

data class ParticlePositionAbsolute(
    val x: Float,
    val y: Float,
) : ParticlePosition()

data object ParticlePositionCenter : ParticlePosition()

data object ParticlePositionStart : ParticlePosition()
