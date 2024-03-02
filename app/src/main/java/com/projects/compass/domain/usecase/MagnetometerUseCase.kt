package com.projects.compass.domain.usecase

import com.projects.compass.domain.model.MagnetometerSensor
import com.projects.compass.domain.repository.SensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MagnetometerUseCase @Inject constructor(
    private val sensorRepository: SensorRepository<MagnetometerSensor>
) {

    val sensorDataFlow = sensorRepository.sensorDataFlow

    fun startListening() {
        sensorRepository.startListening()
    }

    fun stopListening() {
        sensorRepository.stopListening()
    }

}