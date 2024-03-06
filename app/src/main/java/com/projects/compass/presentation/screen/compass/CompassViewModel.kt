package com.projects.compass.presentation.screen.compass

import androidx.lifecycle.ViewModel
import com.projects.compass.domain.usecase.MagnetometerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val magnetometerUseCase: MagnetometerUseCase
) : ViewModel() {

    val sensorDataFlow = magnetometerUseCase.sensorDataFlow

    fun startListening() {
        magnetometerUseCase.startListening()
    }

    fun stopListening() {
        magnetometerUseCase.stopListening()
    }

}