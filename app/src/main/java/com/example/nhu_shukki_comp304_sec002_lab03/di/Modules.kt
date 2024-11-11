package com.example.nhu_shukki_comp304_sec002_lab03.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherAPI
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherDatabase
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherRepository
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherRepositoryImpl
import com.example.nhu_shukki_comp304_sec002_lab03.viewmodel.WeatherViewModel
import com.example.nhu_shukki_comp304_sec002_lab03.workers.WeatherSyncWorker

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val appModules = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
    single { Dispatchers.IO }
    single { WeatherViewModel(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
//            .addConverterFactory(GsonConverterFactory.create())

            .addConverterFactory(
                Json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .build()
    }
    single { get<Retrofit>().create(WeatherAPI::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherDatabase::class.java,
            "weather-database"
        )
            //.build()
/*
            .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                //db.disableWriteAheadLogging();  // Here the solution
                super.onCreate(db)
                Log.d("WeatherDatabase", "Database created successfully.")
            }
            })
//            .fallbackToDestructiveMigration()  // Reset the database upon structural changes
 */
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING) //WRITE_AHEAD_LOGGING
            .build()

    }
    single { get<WeatherDatabase>().weatherDao() }

    worker { WeatherSyncWorker(get(), get(), get()) }

}