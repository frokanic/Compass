package com.projects.compass.data.mapper

import com.projects.compass.domain.model.MagnetometerSensor

class MagnetometerMapperImpl : SensorMapper<MagnetometerSensor> {

    override fun map(values: List<Float>?, accuracy: Int?): MagnetometerSensor {
        return MagnetometerSensor(
            values?.get(0),
            values?.get(1),
            values?.get(2),
            accuracy
        )
    }

}