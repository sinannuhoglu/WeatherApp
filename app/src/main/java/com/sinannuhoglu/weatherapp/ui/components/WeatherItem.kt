package com.sinannuhoglu.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sinannuhoglu.weatherapp.R
import com.sinannuhoglu.weatherapp.model.ForecastItem
import com.sinannuhoglu.weatherapp.model.WeatherResponse
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun WeatherItem(
    weather: WeatherResponse,
    forecastByDay: Map<String, List<ForecastItem>>,
    navController: NavController? = null
) {
    val context = LocalContext.current
    val iconCode = weather.weather.firstOrNull()?.main?.lowercase() ?: "clear"
    val currentHour = LocalDateTime.now(ZoneId.systemDefault()).hour
    val isNight = currentHour < 6 || currentHour > 18
    val iconName = "${iconCode}_${if (isNight) "night" else "daytime"}"
    val iconResId = context.resources.getIdentifier(iconName, "drawable", context.packageName)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val lat = weather.coord.lat
                val lon = weather.coord.lon
                navController?.navigate("detail/$lat/$lon")
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${weather.name}, ${weather.sys.country}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(text = weather.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() }
            ?: "", fontSize = 16.sp, color = Color.White)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconResId != 0) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = "${weather.main.temp.toInt()}°C",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(id = R.string.humidity_label) + ": ${weather.main.humidity}%",
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.pressure_label) + ": ${weather.main.pressure} hPa",
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.wind_label) + ": ${weather.wind.speed} m/s",
                color = Color.White
            )
        }
    }
}
