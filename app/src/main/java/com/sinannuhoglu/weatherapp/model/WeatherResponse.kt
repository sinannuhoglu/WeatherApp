package com.sinannuhoglu.weatherapp.model

data class WeatherResponse(
    val coord: Coord,
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val sys: Sys,
)