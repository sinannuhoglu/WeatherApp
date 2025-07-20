package com.sinannuhoglu.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

fun formatHourFromDateTime(dtTxt: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(dtTxt)
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        outputFormat.format(date ?: Date())
    } catch (e: Exception) {
        "?"
    }
}
