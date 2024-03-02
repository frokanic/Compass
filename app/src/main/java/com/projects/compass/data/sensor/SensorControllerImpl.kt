package com.projects.compass.data.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import com.projects.compass.data.model.SensorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * https://developer.android.com/develop/sensors-and-location/sensors/sensors_overview
 */
class SensorControllerImpl @Inject constructor(
    private val context: Context,
    private val sensorType: Int
) : SensorController {

    /**
     * The SensorManager class is used to create an instance of the sensor service. This
     * provides access to the device's sensor hardware. It is used to access and manage
     * the various available sensors. Provided functionality includes, but is not limited
     * to:
     *  - Access Sensor List: You can either get a list of all the sensors available on
     *  the device, by getSensorList(int type), or a specific sensor, by
     *  getDefaultSensor(int type)
     *
     *  - Register and Unregister Sensor Event Listeners: Done by registerListener and
     *  unregisterListener methods
     *
     * https://developer.android.com/reference/android/hardware/SensorManager
     */
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
    private val containsSensor = deviceSensors.contains(sensorManager.getDefaultSensor(sensorType))

    private var sensor: Sensor? = if (containsSensor) sensorManager.getDefaultSensor(sensorType) else null

    private val _sensorDataFlow = MutableStateFlow(SensorData())
    override val sensorDataFlow: StateFlow<SensorData> = _sensorDataFlow.asStateFlow()

    override fun startListening() {
        if (sensor == null) return

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun stopListening() {
        if (sensor == null) return

        _sensorDataFlow.tryEmit(SensorData())

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (sensor == null) return

        if (event?.sensor?.type == sensorType) {
            _sensorDataFlow.value = _sensorDataFlow.value.copy(values = event.values.toList())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        if (sensor == null) return

        _sensorDataFlow.value = _sensorDataFlow.value.copy(accuracy = accuracy)
    }

}