package com.projects.compass.data.sensor

import android.hardware.SensorEventListener
import com.projects.compass.data.model.SensorData
import kotlinx.coroutines.flow.StateFlow

interface SensorController: SensorEventListener {

    val sensorDataFlow: StateFlow<SensorData>

    fun startListening()

    fun stopListening()

}