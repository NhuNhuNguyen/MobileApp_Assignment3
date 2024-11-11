package com.example.nhu_shukki_comp304_sec002_lab03.navigation

sealed class Screens(val route: String) {
    object LocationsScreen : Screens("locations")
    object LocationDetailsScreen : Screens("locationDetails")
    object FavoriteLocationsScreen : Screens("favoriteLocations")
}
/*
sealed class Screens(val route: String) {
    object PetsScreen : Screens("pets")
    object PetDetailsScreen : Screens("petDetails")
    object FavoritePetsScreen : Screens("favoritePets")
}
*/