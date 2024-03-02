# 🧭 Compass

Compass is an app that serves 2 purposes. First off, it is a compass app 😂. Secondly though, the data layer is written in an abstract way such that, the code can be copy pasted and used for all other sensors. In the future, this might be turned into a library. 

## ✨ Features

- 🧲 Accesses the device's magnetometer sensor
- 🌎 Utilizes sensor data to display a digital compass, guiding users towards magnetic north

## 🚀 Technologies and Libraries Used

- 🎭 [Jetpack Compose](https://developer.android.com/jetpack/compose)
- 🌐 [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Dependency Injection
- 🏛️ Android Clean Architecture
- ⏱️ Coroutines for asynchronous programming

## 🔍 Abstract Logic Explained
The abstract logic mentioned above contains 3 parts. 

#### SensorController
The first one is the ``SensorController`` and its implementation. There, by passing in the constructor the desired sensor type, I start by creating an instance of the SensorManager, and using it to check if such sensor exists in the device. If it exists, the class provides 2 functions, that allow the user to start and stop listening to that sensor, 
as well as an observable, that will allow the user to access the latest values of that sensor.

```kotlin
class SensorControllerImpl @Inject constructor(
    private val context: Context,
    private val sensorType: Int
) : SensorController {
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

//    ...
    
```

## 📷 Screenshots


## 📧 Contact
For any questions or inquiries, feel free to reach out to [f.rokanis@gmail.com](mailto:f.rokanis@gmail.com).
