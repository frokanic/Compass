package com.projects.compass.domain.usecase

import com.projects.compass.domain.model.MagnetometerSensor
import javax.inject.Inject
import kotlin.math.atan2

class SensorToDegreesUseCase @Inject constructor(
    val sensorData: MagnetometerSensor
) {

    operator fun invoke(): Double {
        val angleRadians = atan2(sensorData.y!!.toDouble(), sensorData.x!!.toDouble())

        var degrees = Math.toDegrees(angleRadians)

        if (degrees < 0) {
            degrees += 360
        }

        return degrees
    }

}