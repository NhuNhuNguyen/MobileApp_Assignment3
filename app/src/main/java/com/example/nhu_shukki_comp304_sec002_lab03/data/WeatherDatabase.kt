package com.example.nhu_shukki_comp304_sec002_lab03.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WeatherEntity::class],
    version = 1, // Initial version of the database
    exportSchema = true
)
@TypeConverters(WeatherConverters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}