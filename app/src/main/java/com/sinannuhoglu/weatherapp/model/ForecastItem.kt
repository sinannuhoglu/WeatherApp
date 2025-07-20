package com.sinannuhoglu.weatherapp.model

import com.google.gson.annotations.SerializedName

data class ForecastItem(
    val dt: Long,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)
