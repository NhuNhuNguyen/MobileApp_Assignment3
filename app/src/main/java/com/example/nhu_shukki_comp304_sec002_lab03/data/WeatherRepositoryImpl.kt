package com.example.nhu_shukki_comp304_sec002_lab03.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class WeatherRepositoryImpl(
    private  val weatherAPI: WeatherAPI,
    private val dispatcher: CoroutineDispatcher,
    private val weatherDao: WeatherDao

): WeatherRepository {
    override suspend fun getAllWeather(): Flow<List<WeatherEntity>> {
        return withContext(dispatcher) {
            weatherDao.getAllWeather()
                .map { weatherCached ->
                    weatherCached.map { weatherEntity ->
                        WeatherEntity(
                            latitude = weatherEntity.latitude,
                            longitude = weatherEntity.longitude,
                            generationtimeMs = weatherEntity.generationtimeMs,
                            utcOffsetSeconds = weatherEntity.utcOffsetSeconds,
                            timezone = weatherEntity.timezone,
                            timezoneAbbreviation = weatherEntity.timezoneAbbreviation,
                            elevation = weatherEntity.elevation,
                            currentTemperature = weatherEntity.currentTemperature,
                            currentWeatherCode = weatherEntity.currentWeatherCode,
                            timeUnit = weatherEntity.timeUnit,
                            weatherCodeUnit = weatherEntity.weatherCodeUnit,
                            temperatureMaxUnit = weatherEntity.temperatureMaxUnit,
                            temperatureMinUnit = weatherEntity.temperatureMinUnit,
                            city = weatherEntity.city,
                            dailyTime = weatherEntity.dailyTime,
                            dailyWeatherCode = weatherEntity.dailyWeatherCode,
                            dailyTemperatureMax = weatherEntity.dailyTemperatureMax,
                            dailyTemperatureMin = weatherEntity.dailyTemperatureMin,
                            isFavorite = weatherEntity.isFavorite
                        )
                    }
                }
        }
    }


    override suspend fun fetchRemoteWeather() {
        withContext(dispatcher) {
//            val response = weatherAPI.fetchWeather("43.8668", "-79.2663", "weather_code,temperature_2m_max,temperature_2m_min", "auto")
            weatherDao.deleteAllWeather()
            val responses = listOf(
                weatherAPI.fetchWeather("1.25", "103.75", "temperature_2m,weather_code", "weather_code,temperature_2m_max,temperature_2m_min", "auto"),
                weatherAPI.fetchWeather("51.5", "-0.120000124", "temperature_2m,weather_code", "weather_code,temperature_2m_max,temperature_2m_min", "auto"),
                weatherAPI.fetchWeather("43.70455", "-79.404625", "temperature_2m,weather_code", "weather_code,temperature_2m_max,temperature_2m_min", "auto"),
                weatherAPI.fetchWeather("-33.75", "151.125", "temperature_2m,weather_code", "weather_code,temperature_2m_max,temperature_2m_min", "auto"),
            )

            for(response in responses) {
                if (response.isSuccessful) {
                    Log.d("Weather API", "Weather API called and SUCCESS")
                    val weatherResponse = response.body()

                    if (weatherResponse != null) {
                        try {
                            Log.d("WeatherDao.insert()", "Weather DAO insert begins")

                            val weatherEntity = WeatherEntity(
                                latitude = weatherResponse.latitude,
                                longitude = weatherResponse.longitude,
                                generationtimeMs = weatherResponse.generationtime_ms,
                                utcOffsetSeconds = weatherResponse.utc_offset_seconds,
                                timezone = weatherResponse.timezone,
                                timezoneAbbreviation = weatherResponse.timezone_abbreviation,
                                elevation = weatherResponse.elevation,
                                currentTemperature = weatherResponse.current.temperature_2m,
                                currentWeatherCode = weatherResponse.current.weather_code,
                                timeUnit = weatherResponse.daily_units.time,
                                weatherCodeUnit = weatherResponse.daily_units.weather_code,
                                temperatureMaxUnit = weatherResponse.daily_units.temperature_2m_max,
                                temperatureMinUnit = weatherResponse.daily_units.temperature_2m_min,
                                dailyTime = weatherResponse.daily.daily_time,
                                dailyWeatherCode = weatherResponse.daily.daily_weather_code,
                                dailyTemperatureMax = weatherResponse.daily.daily_temperature_2m_max,
                                dailyTemperatureMin = weatherResponse.daily.daily_temperature_2m_min,
                                city = weatherResponse.timezone.substringAfter("/"),
                                isFavorite = weatherResponse.isFavorite

                            )
                            Log.d("WeatherDao.insert()", "Inserting WeatherEntity: $weatherEntity")
                            weatherDao.insert(weatherEntity)
                            Log.d("WeatherDao.insert()", "Weather DAO insert DONE")

                            //val insertedData = weatherDao.getAllWeather()
                            //Log.d("WeatherDao.insert()", "Inserted WeatherEntity: $insertedData")
                        }catch (e: Exception) {
                            Log.e("Deserialization Error", "Failed to decode JSON: $e")
                        }

                    } else {
                        Log.e(
                            "Weather API response is null",
                            "Error Code: ${response.code()}, Message: ${
                                response.errorBody()?.string()
                            }"
                        )
                    }

                } else {
                    Log.e(
                        "Weather API Error",
                        "Error Code: ${response.code()}, Message: ${response.errorBody()?.string()}"
                    )
                }
            }
            withContext(dispatcher) {
                weatherDao.getAllWeather().collect { insertedData ->
                    Log.d(
                        "WeatherDao.getAllWeather()",
                        "Inserted WeatherEntity: $insertedData"
                    )
                }
            }

        }
    }

    override suspend fun updateFavorite(location: WeatherEntity) {
        //val updatedLocation = location.copy(isFavorite = !location.isFavorite)
        withContext(dispatcher) {
            Log.d("Impl Update", "Updatingxxx in database: ${location.id} ${location.city} - ${location.isFavorite}")

            // Update the database with the modified copy
          //  weatherDao.updateFavorite(updatedLocation)
weatherDao.updateCity(location.city, location.isFavorite)
            Log.d("Impl Update", "Updating in database: ${location.city} - ${location.isFavorite}")
            /*
            weatherDao.updateFavorite(WeatherEntity(
                id = location.id,
                latitude = location.latitude,
                longitude = location.longitude,
                generationtimeMs = location.generationtimeMs,
                utcOffsetSeconds = location.utcOffsetSeconds,
                timezone = location.timezone,
                timezoneAbbreviation = location.timezoneAbbreviation,
                elevation = location.elevation,
                currentTemperature = location.currentTemperature,
                currentWeatherCode = location.currentWeatherCode,
                timeUnit = location.timeUnit,
                weatherCodeUnit = location.weatherCodeUnit,
                temperatureMaxUnit = location.temperatureMaxUnit,
                temperatureMinUnit = location.temperatureMinUnit,
                dailyTime = location.dailyTime,
                dailyWeatherCode = location.dailyWeatherCode,
                dailyTemperatureMax = location.dailyTemperatureMax,
                dailyTemperatureMin = location.dailyTemperatureMin,
                city = location.city,
                isFavorite = location.isFavorite

            ))

             */

        }

        withContext(dispatcher) {
            weatherDao.getAllWeather().collect { updatedData ->
                Log.d(
                    "WeatherDao.getAllWeather()",
                    "UPDATED WeatherEntity: $updatedData"
                )
            }
        }

    }

    override suspend fun getFavoriteLocations(): Flow<List<WeatherEntity>>{
        return withContext(dispatcher) {
            weatherDao.getFavoriteLocations()
                .map { weatherCached ->
                    weatherCached.map { weatherEntity ->
                        WeatherEntity(
                            latitude = weatherEntity.latitude,
                            longitude = weatherEntity.longitude,
                            generationtimeMs = weatherEntity.generationtimeMs,
                            utcOffsetSeconds = weatherEntity.utcOffsetSeconds,
                            timezone = weatherEntity.timezone,
                            timezoneAbbreviation = weatherEntity.timezoneAbbreviation,
                            elevation = weatherEntity.elevation,
                            currentTemperature = weatherEntity.currentTemperature,
                            currentWeatherCode = weatherEntity.currentWeatherCode,
                            timeUnit = weatherEntity.timeUnit,
                            weatherCodeUnit = weatherEntity.weatherCodeUnit,
                            temperatureMaxUnit = weatherEntity.temperatureMaxUnit,
                            temperatureMinUnit = weatherEntity.temperatureMinUnit,
                            city = weatherEntity.city,
                            dailyTime = weatherEntity.dailyTime,
                            dailyWeatherCode = weatherEntity.dailyWeatherCode,
                            dailyTemperatureMax = weatherEntity.dailyTemperatureMax,
                            dailyTemperatureMin = weatherEntity.dailyTemperatureMin,
                            isFavorite = weatherEntity.isFavorite
                        )
                    }
                }
        }

    }

    override suspend fun getLocationDetails(locationId: String): WeatherEntity {
        return withContext(dispatcher) {
            weatherDao.getLocationDetails(locationId)
        }
    }


}