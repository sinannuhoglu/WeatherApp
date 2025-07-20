package com.sinannuhoglu.weatherapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinannuhoglu.weatherapp.model.ForecastItem
import com.sinannuhoglu.weatherapp.model.WeatherResponse
import com.sinannuhoglu.weatherapp.network.WeatherRepository
import com.sinannuhoglu.weatherapp.util.CityListProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _forecastData = MutableStateFlow<Map<String, List<ForecastItem>>>(emptyMap())
    val forecastData: StateFlow<Map<String, List<ForecastItem>>> = _forecastData

    private val _nearbyWeather = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val nearbyWeather: StateFlow<List<WeatherResponse>> = _nearbyWeather

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _weatherData.value = repository.getWeatherData(lat, lon)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val forecast = repository.getForecastData(lat, lon)
                val grouped =
                    forecast.list.groupBy { it.dtTxt.substring(0, 10) }.toList().take(5).toMap()
                _forecastData.value = grouped
            } catch (e: Exception) {
                _error.value = "Tahmin verisi alınamadı"
            }
        }
    }

    fun fetchNearbyCitiesWeather(countryCode: String) {
        viewModelScope.launch {
            try {
                val cities = CityListProvider.getCitiesByCountry(countryCode)
                val weatherList = cities.mapNotNull { city ->
                    try {
                        repository.getWeatherByCity(city)
                    } catch (_: Exception) {
                        null
                    }
                }
                _nearbyWeather.value = weatherList
            } catch (e: Exception) {
                _error.value = "Yakın şehirler alınamadı"
            }
        }
    }
}
