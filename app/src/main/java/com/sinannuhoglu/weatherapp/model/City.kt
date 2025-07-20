package com.sinannuhoglu.weatherapp.model

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coord
)