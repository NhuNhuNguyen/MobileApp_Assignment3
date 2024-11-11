package com.example.nhu_shukki_comp304_sec002_lab03

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.nhu_shukki_comp304_sec002_lab03.ui.theme.Nhu_ShukKi_COMP304_Sec002_Lab03Theme
import com.example.nhu_shukki_comp304_sec002_lab03.views.WeatherList
import com.example.nhu_shukki_comp304_sec002_lab03.workers.WeatherSyncWorker
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherEntity
import com.example.nhu_shukki_comp304_sec002_lab03.data.WeatherDao


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startWeatherSync()



        val dbPath = applicationContext.getDatabasePath("weather-database")
        if (dbPath.exists()) {
            Log.d("WeatherDatabase", "Database file exists at: ${dbPath.absolutePath}")
        } else {
            Log.d("WeatherDatabase", "Database file does NOT exist.")
        }


        enableEdgeToEdge()
        setContent {
            Nhu_ShukKi_COMP304_Sec002_Lab03Theme {
                Scaffold(
                    topBar = { PacktCenterAlignedTopBar() },
                    bottomBar = { PacktBottomNavigationBar() },
                    content =  { paddingValues ->
                        WeatherList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        )
                    }
                )
            }
        }
    }

    private fun startWeatherSync() {
        val syncWeatherWorkRequest = OneTimeWorkRequestBuilder<WeatherSyncWorker>()

            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
            "WeatherSyncWorker",
            ExistingWorkPolicy.REPLACE, // KEEP/ REPLACE
            syncWeatherWorkRequest
        )
    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacktCenterAlignedTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text("Weather Watch",
            color = Color.White, // Set the text color to white
            fontWeight = FontWeight.Bold // Set the text weight to bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun PacktBottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { /* Handle navigation item click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = false,
            onClick = { /* Handle navigation item click */ }
        )
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Nhu_ShukKi_COMP304_Sec002_Lab03Theme {
        Greeting("Android")
    }
}
/*
Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    Greeting(
        name = "Android",
        modifier = Modifier.padding(innerPadding)
    )
}
*/