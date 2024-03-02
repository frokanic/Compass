package com.projects.compass.domain.repository

import kotlinx.coroutines.flow.Flow

interface SensorRepository<T> {

    val sensorDataFlow: Flow<T>

    fun startListening()

    fun stopListening()

}