package com.projects.compass.presentation.extension

import com.projects.compass.domain.model.MagnetometerSensor
import kotlin.math.atan2
import kotlin.math.roundToInt

/**
 * Converts the magnetic sensor x and y values to a compass heading in degrees.
 * This extension function is defined in the presentation layer to be used for UI-related transformations.
 *
 * @return Compass heading in degrees, normalized to the range [0, 360).
 */
fun MagnetometerSensor.toCompassDegreesRounded(): Int {
    return if (this.y != null && this.x != null) {
        val angleRadians = atan2(this.y.toDouble(), this.x.toDouble())

        var degrees = Math.toDegrees(angleRadians).roundToInt()

        if (degrees < 0) {
            degrees += 360
        }

        degrees
    } else {
        -1
    }
}