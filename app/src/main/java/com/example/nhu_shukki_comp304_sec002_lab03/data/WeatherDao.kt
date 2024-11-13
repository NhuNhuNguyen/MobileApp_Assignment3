package com.example.nhu_shukki_comp304_sec002_lab03.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM Weather")
    fun getAllWeather(): Flow<List<WeatherEntity>>

    @Query("DELETE FROM Weather")
    suspend fun deleteAllWeather()

    @Update
    suspend fun updateFavorite(weatherEntity: WeatherEntity)

    @Query("UPDATE Weather SET isFavorite = :isFav WHERE city = :city")
    suspend fun updateCity(city: String, isFav: Boolean)

    @Query("SELECT * FROM Weather WHERE isFavorite = 1")
    fun getFavoriteLocations(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM Weather where city = :id")
    fun getLocationDetails(id: String): WeatherEntity
}