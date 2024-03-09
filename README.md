# 🧭 Compass

Compass is an app that serves 2 purposes. First off, it is a compass app 😂. Secondly though, the data layer is written in an abstract way such that, the code can be copy pasted and used for all other sensors. In the future, this might be turned into a library. 

## 🚧 UI Under Construction

The user interface for this app is currently under construction. Stay tuned for updates as we work to bring you an intuitive and visually appealing experience.


## ✨ Features

- 🧲 Accesses the device's magnetometer sensor
- 🌎 Utilizes sensor data to display a digital compass, guiding users towards magnetic north

## 🚀 Technologies and Libraries Used

- 🎭 [Jetpack Compose](https://developer.android.com/jetpack/compose)
- 🌐 [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Dependency Injection
- 🏛️ Android Clean Architecture
- ⏱️ Coroutines for asynchronous programming

## 🔍 Abstract Logic Explained
The abstract logic mentioned above contains 4 parts. 

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

#### SensorData
Since sensors always return 2 important values, 1 with a list of floats (can be euclidean coordinates, light or humidity related data, or a plethora of other values), and an accuracy integer value, a data class with the purpose of encapsulating all those instances was created. The sensor the data came from and what they represent will be a concern of the domain layer 

```kotlin
data class SensorData(
    val values: List<Float>? = null,
    val accuracy: Int? = null
)
```
    

#### SensorRepositoryImpl
The purpose of that is to simply expose the above discussed functionality of ``SensorController`` to the domain layer, while mapping the data to the corresponding domain model. Generics were used to keep the repository an abstract access point to the domain layer, instead of having it being sensor specific.


#### SensorMapper
Its purpose is to help with the functionality of the ``SensorRepositoryImpl``, while respecting the separation of concerns principale. It maps the data layer model discussed above, to the domain's, sensor specific model. Here, we create implementations of the SensorMapper interface, specific to the domain model we want to map to (and thus, specific to the desired sensor).

### Why this matters?
Given the above structure, one can copy and paste the whole data layer. Then, just by:
    1. Creating an instance of SensorController (ideally through dependency injection, for more clear code), providing his/her desired sensor type.
    2. Creating a domain model (or in general a data class), specific to the sensor.
    3. Creating a SensorMapper implementation specific to the above domain model
he/she can start and stop listening to the sensor, as well as collect its latest data, just by calling the 2 repository's below functions, and observing the repository's below observable.

``startListening()``
``stopListening()``
``sensorDataFlow``

## 📷 Screenshots


## 📧 Contact
For any questions or inquiries, feel free to reach out to [f.rokanis@gmail.com](mailto:f.rokanis@gmail.com).
