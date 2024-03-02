package com.projects.compass.data.mapper

interface SensorMapper<T> {
    fun map(values: List<Float>?, accuracy: Int?): T
}
