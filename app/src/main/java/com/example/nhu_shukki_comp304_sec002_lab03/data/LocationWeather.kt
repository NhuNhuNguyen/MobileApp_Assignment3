package com.example.nhu_shukki_comp304_sec002_lab03.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationWeather (
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("generationtime_ms")
    val generationtime_ms: Double,
    @SerialName("utc_offset_seconds")
    val utc_offset_seconds: Int,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezone_abbreviation: String,
    @SerialName("elevation")
    val elevation: Double,
    @SerialName("current_units")
    val currentUnits: CurrentUnits,
    @SerialName("current")
    val current: Current,
    @SerialName("daily_units")
    val daily_units: DailyUnits,
    @SerialName("daily")
    val daily: Daily,
    val isFavorite: Boolean = false

)

@Serializable
data class CurrentUnits(
    @SerialName("time")
    val time: String,
    @SerialName("interval")
    val interval: String,
    @SerialName("temperature_2m")
    val temperature_2m: String,
    @SerialName("weather_code")
    val weather_code: String,
)

@Serializable
data class Current(
    @SerialName("time")
    val time: String,
    @SerialName("interval")
    val interval: Double,
    @SerialName("temperature_2m")
    val temperature_2m: Double,
    @SerialName("weather_code")
    val weather_code: Int
)

@Serializable
data class DailyUnits(
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weather_code: String,
    @SerialName("temperature_2m_max")
    val temperature_2m_max: String,
    @SerialName("temperature_2m_min")
    val temperature_2m_min: String
)

@Serializable
data class Daily(
    @SerialName("time")
    val daily_time: List<String>,
    @SerialName("weather_code")
    val daily_weather_code: List<Int>,
    @SerialName("temperature_2m_max")
    val daily_temperature_2m_max: List<Double>,
    @SerialName("temperature_2m_min")
    val daily_temperature_2m_min: List<Double>
)

