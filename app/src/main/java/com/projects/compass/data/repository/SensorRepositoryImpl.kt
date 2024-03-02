package com.projects.compass.data.repository

import com.projects.compass.data.sensor.SensorController
import com.projects.compass.data.mapper.SensorMapper
import com.projects.compass.domain.repository.SensorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SensorRepositoryImpl<T>(
    private val sensorController: SensorController,
    private val sensorMapper: SensorMapper<T>
) : SensorRepository<T> {

    override val sensorDataFlow: Flow<T> = sensorController.sensorDataFlow.map { sensorData ->
        sensorMapper.map(sensorData.values, sensorData.accuracy)
    }

        override fun startListening() {
        sensorController.startListening()
    }

    override fun stopListening() {
        sensorController.stopListening()
    }

}