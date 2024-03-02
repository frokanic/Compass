package com.projects.compass.presentation.compass

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.projects.compass.domain.model.MagnetometerSensor

@Composable
fun CompassScreen(
    viewModel: CompassViewModel = hiltViewModel()
) {
    viewModel.startListening()

    val sensorData = viewModel.sensorDataFlow.collectAsState(MagnetometerSensor(null, null, null, null))

    Column {
        sensorData.value.let { values ->
            Text(text = "X: ${values.x ?: "N/A"}")
            Text(text = "Y: ${values.y ?: "N/A"}")
            Text(text = "Z: ${values.z ?: "N/A"}")
            Text(text = "Accuracy: ${values.accuracy ?: "N/A"}")
        }
    }
}