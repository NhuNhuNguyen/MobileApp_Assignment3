package com.example.nhu_shukki_comp304_sec002_lab03.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nhu_shukki_comp304_sec002_lab03.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteLocationsScreen(
    modifier: Modifier,

    ){
    val weatherViewModel: WeatherViewModel = koinViewModel()
//    val locationsUIState by weatherViewModel.locationsUIState.collectAsStateWithLifecycle()
    val favLocations by weatherViewModel.favoriteLocations.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        item{
            Text(
                text = "Favorite locations",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF3A3A3A),
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 8.dp)
//                    .clickable { showCard = true }
            )}
        items(favLocations) { favLocation ->
            val isFavorite = favLocation.isFavorite

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
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
                        text = favLocation.city,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 30.sp,
                        ),
                        color = Color(0xFF333333),
                        modifier = Modifier
                            .padding(end = 8.dp)
//                    .clickable { showCard = true }
                    )
                    Text(
                        text = "${favLocation.currentTemperature.toString()} ${favLocation.temperatureMaxUnit}",
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
                        var updatedLocation = favLocation
                        updatedLocation.isFavorite = !updatedLocation.isFavorite
                        weatherViewModel.updateFavorite(updatedLocation)
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

//                LocationListItem(
//                location = location ,
//                onLocationClicked = onLocationClicked,
//               onFavoriteClicked = onFavoriteClicked
//            )
        }
    }

}
