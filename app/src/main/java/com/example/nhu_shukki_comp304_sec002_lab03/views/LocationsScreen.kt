package com.example.nhu_shukki_comp304_sec002_lab03.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nhu_shukki_comp304_sec002_lab03.data.LocationWeather
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherEntity

@Composable
fun LocationsScreen(
    onLocationClicked: (WeatherEntity) -> Unit,
    locations: List<WeatherEntity>,
    modifier: Modifier,
    onFavoriteClicked: (WeatherEntity) -> Unit

){
    LazyColumn(
        modifier = modifier
    ) {
        items(locations) { location ->
            LocationListItem(
                location = location ,
                onLocationClicked = onLocationClicked,
               onFavoriteClicked = onFavoriteClicked
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LocationListItem(
    location: WeatherEntity,
    onLocationClicked: (WeatherEntity) -> Unit,
    onFavoriteClicked: (WeatherEntity) -> Unit
) {
}