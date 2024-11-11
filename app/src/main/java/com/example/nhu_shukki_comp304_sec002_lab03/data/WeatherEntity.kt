package com.example.nhu_shukki_comp304_sec002_lab03.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import androidx.room.TypeConverters


@Entity(tableName = "Weather")
@TypeConverters(WeatherConverters::class)
data class WeatherEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val generationtimeMs: Double,
    val utcOffsetSeconds: Int,
    val timezone: String,
    val timezoneAbbreviation: String,
    val elevation: Double,
    val timeUnit: String,  // ISO8601 date strings
    val weatherCodeUnit: String,
    val temperatureMaxUnit: String,
    val temperatureMinUnit: String,
    val dailyTime: List<String>,
    val dailyWeatherCode: List<Int>,
    val dailyTemperatureMax: List<Double>,
    val dailyTemperatureMin: List<Double>,
    val city: String,
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean

/*
    val time: List<String>,  // ISO8601 date strings
    val weatherCode: List<Int>,
    val temperatureMax: List<Double>,
    val temperatureMin: List<Double>
*/
)

