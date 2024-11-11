package com.example.nhu_shukki_comp304_sec002_lab03.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherRepository

class WeatherSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val  weatherRepository: WeatherRepository
): CoroutineWorker(appContext, workerParams)  {
    override suspend fun doWork(): Result {
        try {
            Log.d("WeatherSyncWorker", "Starting weather sync task.")
            weatherRepository.fetchRemoteWeather()
            Log.d("WeatherSyncWorker", "Weather sync task DONE.")
            return Result.success()
        } catch (e: Exception) {
            Log.e("WeatherSyncWorker", "Error occurred: ${e.message}", e)
            return Result.failure()
        }
    }
}