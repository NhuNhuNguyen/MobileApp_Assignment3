package com.example.nhu_shukki_comp304_sec002_lab03.data

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getAllWeather(): Flow<List<WeatherEntity>>
    suspend fun fetchRemoteWeather()

}