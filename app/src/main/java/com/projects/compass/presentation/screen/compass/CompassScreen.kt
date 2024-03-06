package com.projects.compass.presentation.screen.compass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.projects.compass.domain.model.MagnetometerSensor
import com.projects.compass.presentation.extension.toCompassDegreesRounded

@Composable
fun CompassScreen(
    viewModel: CompassViewModel = hiltViewModel()
) {

    Row {

    }
//    viewModel.startListening()
//
//    val sensorData = viewModel.sensorDataFlow.collectAsState(MagnetometerSensor(null, null, null, null))
//
//    Column {
//        sensorData.value.let { values ->
//            Text(text = "X: ${values.x ?: "N/A"}")
//            Text(text = "Y: ${values.y ?: "N/A"}")
//            Text(text = "Z: ${values.z ?: "N/A"}")
//            Text(text = "Accuracy: ${values.accuracy ?: "N/A"}")
//            Text(text = "Degrees: ${values.toCompassDegreesRounded()}°")
//        }
//    }
}