package com.example.nhu_shukki_comp304_sec002_lab03.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.nhu_shukki_comp304_sec002_lab03.data.LocationWeather
import com.example.nhu_shukki_comp304_sec002_lab03.data.NetworkResult
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherEntity
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherRepository
import com.example.nhu_shukki_comp304_sec002_lab03.data.asResult
import com.example.nhu_shukki_comp304_sec002_lab03.views.LocationsUIState


class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {
    val locationsUIState = MutableStateFlow(LocationsUIState())
    private val _favoriteLocations = MutableStateFlow<List<WeatherEntity>>(emptyList())
    val favoriteLocations: StateFlow<List<WeatherEntity>> = _favoriteLocations

    val emptyWeatherEntity = WeatherEntity(
        id = 0,  // Default primary key value (auto-generated)
        latitude = 0.0,
        longitude = 0.0,
        generationtimeMs = 0.0,
        utcOffsetSeconds = 0,
        timezone = "",
        timezoneAbbreviation = "",
        elevation = 0.0,
        currentTemperature = 0.0,
        currentWeatherCode = 0,
        timeUnit = "",
        weatherCodeUnit = "",
        temperatureMaxUnit = "",
        temperatureMinUnit = "",
        dailyTime = emptyList(),
        dailyWeatherCode = emptyList(),
        dailyTemperatureMax = emptyList(),
        dailyTemperatureMin = emptyList(),
        city = "xxxx",
        isFavorite = false  // Default value for the favorite flag
    )
    private val _locationDetails = MutableStateFlow<WeatherEntity>(emptyWeatherEntity)
    val locationDetails: StateFlow<WeatherEntity> = _locationDetails

    init{
        getAllWeather()
        getFavoriteLocations()
    }

    private fun getAllWeather(){
        viewModelScope.launch {
            if (!locationsUIState.value.dataLoaded) {

                weatherRepository.getAllWeather().asResult().collect { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            locationsUIState.update {
                                it.copy(isLoading = false, locations = result.data, dataLoaded = true)
                            }
                        }

                        is NetworkResult.Error -> {
                            locationsUIState.update {
                                it.copy(isLoading = false, error = result.error)
                            }
                        }
                    }
                }
            }
        }

    }

    fun updateFavorite(location: WeatherEntity) {
        //val updatedLocation = location.copy(isFavorite = !location.isFavorite)

        // Update the location's isFavorite flag in the database
        viewModelScope.launch {
            Log.d("ViewModel Update", "Updating in database: ${location.city} - ${location.isFavorite}")
            weatherRepository.updateFavorite(location)
/*
            Log.d("WeatherViewModel - Update", "Updating in database: $updatedLocation")
            weatherRepository.updateFavorite(updatedLocation)
            Log.d("WeatherViewModel - Update", "Database update completed for: ${updatedLocation.id}")

            // Update the UI state after the database update
            locationsUIState.update { state ->
                val updatedLocations = state.locations.map {
                    Log.d("WeatherViewModel - Update", "loop to: ${it.id} - ${it.city}, update: ${updatedLocation.id} - ${updatedLocation.city}")
                    if (it.city == location.city)
                        updatedLocation
                    else
                        it

                }
                Log.d("WeatherViewModel - Update", "Before copy: ${updatedLocation.id}")
                Log.d("WeatherViewModel - Update", "Before copy: ${updatedLocations}")
                state.copy(locations = updatedLocations)
                Log.d("WeatherViewModel - Update", "After copy: ${state}")
            }

 */
        }
    }

    fun getFavoriteLocations() {
        viewModelScope.launch {
            weatherRepository.getFavoriteLocations().collect { favoriteList ->
                _favoriteLocations.value = favoriteList

            }
        }
    }

    fun getLocationDetails(locationId: String) {
        viewModelScope.launch {
            // Assuming you are fetching the location details from the repository
            val specificDetails = weatherRepository.getLocationDetails(locationId)
            _locationDetails.value = specificDetails
        }
    }
}