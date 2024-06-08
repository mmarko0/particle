package com.neonduck.particle.engine.extensions

import kotlin.random.Random

fun Random.nextFloat(from: Float, to: Float) =
    Random.nextDouble(from.toDouble(), to.toDouble()).toFloat()
