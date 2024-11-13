package com.example.nhu_shukki_comp304_sec002_lab03.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherEntity
import com.example.nhu_shukki_comp304_sec002_lab03.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationDetailsScreen(
    locationId: String
    //navBackStackEntry: NavBackStackEntry
){
    Text(
        text = "LOADING...",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .padding(16.dp)
    )


    //    val locationId = navBackStackEntry.arguments?.getString("locationId")
    Log.d("load (input)", "get details for ${locationId}")
    val weatherViewModel: WeatherViewModel = koinViewModel()
//    weatherViewModel.getLocationDetails(locationId ?: "")
    weatherViewModel.getLocationDetails(locationId)
    val locationDetails by weatherViewModel.locationDetails.collectAsStateWithLifecycle()
    Log.d("load (from db)", "get details for $locationDetails")
    var isFavorite by remember { mutableStateOf(locationDetails.isFavorite) }

    if (locationDetails.city.isEmpty()) {
        // Loading state
        Text(
            text = "LOADING...",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp)
        )
    } else {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
//                    .clickable { showCard = !showCard },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
//                .height(100.dp)

        ) {
            Text(
                text = "LOADING...",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(end = 8.dp)
//                    .clickable { showCard = true }
            )
            Text(
                text = "${locationDetails.city} DETAILS",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(end = 8.dp)
//                    .clickable { showCard = true }
            )
            Text(
                text = locationDetails.currentTemperature.toString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }

        IconButton(
            onClick = {
                Log.d("Before set Favorite", "${locationDetails.isFavorite}")
                isFavorite = !isFavorite
                locationDetails.copy(isFavorite = !locationDetails.isFavorite)
                Log.d("After set Favorite", "${locationDetails.isFavorite}")
                //var updatedLocation = location
                //updatedLocation.isFavorite = !updatedLocation.isFavorite
                weatherViewModel.updateFavorite(locationDetails.copy(isFavorite = isFavorite))
            },
            content = {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray // Set the icon color to red
                )
            }
        )

    }}



}