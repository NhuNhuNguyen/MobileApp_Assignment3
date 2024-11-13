package com.example.nhu_shukki_comp304_sec002_lab03.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

//import com.packt.chaptereight.views.FavoritePetsScreen
//import com.packt.chaptereight.views.PetDetailsScreen
//import com.packt.chaptereight.views.PetsScreen

import com.example.nhu_shukki_comp304_sec002_lab03.views.LocationsScreen
import com.example.nhu_shukki_comp304_sec002_lab03.views.LocationDetailsScreen
import com.example.nhu_shukki_comp304_sec002_lab03.views.FavoriteLocationsScreen


import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
/*
@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.LocationsScreen.route
    ) {
//        composable(Screens.LocationsScreen.route) {
//            LocationsScreen(
//
//            )
//        }

        composable(
            route = "${Screens.LocationDetailsScreen.route}/{location}",
        ) {
            LocationDetailsScreen(

            )
        }

//        composable(Screens.FavoriteLocationsScreen.route) {
//            FavoriteLocationsScreen(
//
//            )
//        }
    }
}

 */