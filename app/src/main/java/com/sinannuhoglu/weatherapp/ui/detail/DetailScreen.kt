package com.sinannuhoglu.weatherapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.sinannuhoglu.weatherapp.ui.components.ForecastItemCard
import com.sinannuhoglu.weatherapp.ui.components.WeatherItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    lat: Double,
    lon: Double,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
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
            TopAppBar(modifier = Modifier.padding(top = 8.dp), title = {
                Text(
                    text = stringResource(R.string.detail_screen_title),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }, actions = {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
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

                error != null -> Text(
                    text = error ?: stringResource(R.string.error_message),
                    color = MaterialTheme.colorScheme.error
                )

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

                        WeatherItem(weather = currentWeather, forecastByDay = groupedForecast)

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.hourly_forecast),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(todayForecasts) { forecast ->
                                ForecastItemCard(forecastItem = forecast)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.daily_forecast),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        otherDays.forEach { (date, forecasts) ->
                            Text(
                                text = date,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(forecasts) { forecast ->
                                    ForecastItemCard(forecastItem = forecast)
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}
