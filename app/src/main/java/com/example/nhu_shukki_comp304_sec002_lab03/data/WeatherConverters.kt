package com.example.nhu_shukki_comp304_sec002_lab03.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WeatherConverters {
    //private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromIntList(value: List<Int>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromDoubleList(value: List<Double>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toDoubleList(value: String): List<Double> {
        return Json.decodeFromString(value)
    }


}