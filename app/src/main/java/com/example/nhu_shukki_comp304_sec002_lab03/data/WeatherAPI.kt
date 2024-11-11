package com.example.nhu_shukki_comp304_sec002_lab03.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast")
    suspend fun fetchWeather(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("daily") daily: String,
        @Query("timezone") timezone: String,
    ): Response<LocationWeather>
//    ): Response<List<LocationWeather>>
}