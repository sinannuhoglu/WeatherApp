package com.sinannuhoglu.weatherapp.network

import com.sinannuhoglu.weatherapp.model.ForecastResponse
import com.sinannuhoglu.weatherapp.model.WeatherResponse
import com.sinannuhoglu.weatherapp.util.Constants
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApiService
) {
    suspend fun getWeatherData(lat: Double, lon: Double) = api.getWeatherData(lat, lon, Constants.API_KEY)

    suspend fun getWeatherByCity(city: String) = api.getWeatherByCity(city, Constants.API_KEY)

    suspend fun getForecastData(lat: Double, lon: Double) = api.getForecastData(lat, lon, Constants.API_KEY)
}
