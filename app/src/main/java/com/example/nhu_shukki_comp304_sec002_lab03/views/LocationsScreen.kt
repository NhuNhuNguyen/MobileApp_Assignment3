package com.example.nhu_shukki_comp304_sec002_lab03.views

import android.util.Log
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nhu_shukki_comp304_sec002_lab03.data.LocationWeather
import com.example.nhu_shukki_comp304_sec002_lab03.viewmodel.WeatherViewModel
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherEntity
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherRepository
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherRepositoryImpl
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsScreen(
//    onLocationClicked: (WeatherEntity) -> Unit,
//    locations: List<WeatherEntity>,
    modifier: Modifier,
    navController : NavController,
//    onFavoriteClicked: (WeatherEntity) -> Unit
//    weatherViewModel: WeatherViewModel = koinViewModel()

){
    val weatherViewModel: WeatherViewModel = koinViewModel()
    val locationsUIState by weatherViewModel.locationsUIState.collectAsStateWithLifecycle()
    val locations = locationsUIState.locations

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        item{
        Text(
            text = "All locations",
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color(0xFF3A3A3A),
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 8.dp)
//                    .clickable { showCard = true }
        )}

        items(locations) { location ->
            //val isFavorite = location.isFavorite
            var isFavorite by remember { mutableStateOf(location.isFavorite) }

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("locationDetailsScreen/${location.city}"
                        )
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Start,
//                backgroundColor = MaterialTheme.colorScheme.surfaceVariant

            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .height(100.dp)

                ) {
                    Text(
                        text = location.city,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 30.sp,
                        ),
                        color = Color(0xFF333333),
                        modifier = Modifier
                            .padding(end = 8.dp)
//                    .clickable { showCard = true }
                    )
                    Text(
                        text = "${location.currentTemperature.toString()} ${location.temperatureMaxUnit}",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color(0xFF333333),
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }

                IconButton(
                    onClick = {
                        Log.d("Before set Favorite", "${location.isFavorite}")
                        isFavorite = !isFavorite
                        location.copy(isFavorite = !location.isFavorite)
                        Log.d("After set Favorite", "${location.isFavorite}")
                        //var updatedLocation = location
                        //updatedLocation.isFavorite = !updatedLocation.isFavorite
                        weatherViewModel.updateFavorite(location.copy(isFavorite = isFavorite))
                    },
                    content = {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color.Red else Color.Gray // Set the icon color to red
                        )
                    }
                )}

            }

//                LocationListItem(
//                location = location ,
//                onLocationClicked = onLocationClicked,
//               onFavoriteClicked = onFavoriteClicked
//            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LocationListItem(
    location: WeatherEntity,
//    onLocationClicked: (WeatherEntity) -> Unit,
//    onFavoriteClicked: (WeatherEntity) -> Unit
) {
}