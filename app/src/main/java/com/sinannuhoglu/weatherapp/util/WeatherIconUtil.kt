package com.sinannuhoglu.weatherapp.util

fun getDrawableResourceName(weatherMain: String?, dtTxt: String?): String {
    val isDay = dtTxt?.let {
        it.contains("06") || it.contains("09") || it.contains("12") || it.contains("15") || it.contains(
            "18"
        )
    } ?: true

    return when {
        weatherMain?.contains(
            "cloud", ignoreCase = true
        ) == true -> "clouds_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains(
            "clear", ignoreCase = true
        ) == true -> "clear_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains(
            "rain", ignoreCase = true
        ) == true -> "rain_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains(
            "drizzle", ignoreCase = true
        ) == true -> "drizzle_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains(
            "snow", ignoreCase = true
        ) == true -> "snow_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains(
            "thunderstorm", ignoreCase = true
        ) == true -> "thunderstorm_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains("mist", ignoreCase = true) == true || weatherMain?.contains(
            "fog", ignoreCase = true
        ) == true -> "mist_${if (isDay) "daytime" else "night"}"

        weatherMain?.contains(
            "wind", ignoreCase = true
        ) == true -> "windy_${if (isDay) "daytime" else "night"}"

        else -> "clear_${if (isDay) "daytime" else "night"}"
    }
}
