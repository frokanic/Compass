package com.projects.compass.di

import android.content.Context
import android.hardware.Sensor
import com.projects.compass.data.mapper.MagnetometerMapperImpl
import com.projects.compass.data.repository.SensorRepositoryImpl
import com.projects.compass.data.sensor.SensorController
import com.projects.compass.data.sensor.SensorControllerImpl
import com.projects.compass.data.mapper.SensorMapper
import com.projects.compass.domain.model.MagnetometerSensor
import com.projects.compass.domain.repository.SensorRepository
import com.projects.compass.domain.usecase.MagnetometerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("MagnetometerSensor")
    fun provideMagnetometerSensorController(
        @ApplicationContext context: Context
    ): SensorController = SensorControllerImpl(context, Sensor.TYPE_MAGNETIC_FIELD)

    @Singleton
    @Provides
    @Named("MagnetometerMapper")
    fun provideSensorMapper(
        @ApplicationContext context: Context
    ): SensorMapper<MagnetometerSensor> = MagnetometerMapperImpl()

    @Singleton
    @Provides
    @Named("MagnetometerRepository")
    fun provideMagnetometerSensorRepository(
        @Named("MagnetometerSensor") magnetometerSensorController: SensorController,
        @Named("MagnetometerMapper") sensorMapper: SensorMapper<MagnetometerSensor>,
    ): SensorRepository<MagnetometerSensor> = SensorRepositoryImpl(magnetometerSensorController, sensorMapper)

    @Singleton
    @Provides
    fun provideMagnetometerUseCase(
        @Named("MagnetometerRepository") magnetometerSensorRepository: SensorRepository<MagnetometerSensor>
    ): MagnetometerUseCase = MagnetometerUseCase(magnetometerSensorRepository)

}