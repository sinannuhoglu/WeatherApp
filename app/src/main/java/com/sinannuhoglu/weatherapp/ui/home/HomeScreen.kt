package com.sinannuhoglu.weatherapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sinannuhoglu.weatherapp.R
import com.sinannuhoglu.weatherapp.ui.components.HomeMapPreview
import com.sinannuhoglu.weatherapp.ui.components.WeatherCardItem
import com.sinannuhoglu.weatherapp.ui.components.NearbyWeatherItem
import com.sinannuhoglu.weatherapp.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    lat: Double,
    lon: Double,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val weatherData by viewModel.weatherData.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val nearbyWeather by viewModel.nearbyWeather.collectAsState()
    val forecastData by viewModel.forecastData.collectAsState()

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0b327a), Color(0xFF134bb2), Color(0xFF1148b0)
        )
    )

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(lat, lon)
        viewModel.fetchForecast(lat, lon)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 8.dp), title = {
                    Text(
                        text = stringResource(R.string.home_screen_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }, containerColor = Color.Transparent
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
                .padding(innerPadding), contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> CircularProgressIndicator()

                error != null -> {
                    Text(
                        text = error ?: stringResource(R.string.error_message),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                weatherData != null -> {
                    val currentWeather = weatherData!!
                    val countryCode = currentWeather.sys.country

                    LaunchedEffect(countryCode) {
                        viewModel.fetchNearbyCitiesWeather(countryCode)
                    }

                    val groupedForecast = forecastData
                    val today = groupedForecast.keys.firstOrNull()
                    val todayForecasts = groupedForecast[today] ?: emptyList()
                    val otherDays = groupedForecast.filterKeys { it != today }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        WeatherCardItem(weather = currentWeather, navController = navController)

                        Text(text = stringResource(R.string.map_hint_text),
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable {
                                    navController.navigate("map/$lat/$lon")
                                })

                        HomeMapPreview(lat = lat, lon = lon, onMapClick = {
                            navController.navigate("map/$lat/$lon")
                        })

                        Text(
                            text = stringResource(R.string.other_cities),
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 500.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(nearbyWeather) { item ->
                                NearbyWeatherItem(
                                    cityName = item.name,
                                    temp = "${item.main.temp.toInt()}°C",
                                    humidity = "${item.main.humidity}%",
                                    pressure = "${item.main.pressure} hPa",
                                    windSpeed = "${item.wind.speed} m/s",
                                    weatherMain = item.weather.firstOrNull()?.main.orEmpty(),
                                    lat = item.coord.lat,
                                    lon = item.coord.lon,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
