package com.example.nhu_shukki_comp304_sec002_lab03.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nhu_shukki_comp304_sec002_lab03.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherList(modifier: Modifier) {
//    val weatherViewModel: WeatherViewModel = koinViewModel()
    //locations: List<LocationWeather>,
    LazyColumn(
        modifier = modifier
    ) {
        items(10) { index ->
            Text(text = "Weather of city: $index")
        }
/*
        items(petsViewModel.getPets()) { pet ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Name: ${pet.name}")
                Text(text = "Species: ${pet.species}")
            }
        }

 */
    }
}