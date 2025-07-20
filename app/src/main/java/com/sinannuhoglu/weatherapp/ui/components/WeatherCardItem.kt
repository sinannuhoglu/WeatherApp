package com.sinannuhoglu.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sinannuhoglu.weatherapp.model.WeatherResponse
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun WeatherCardItem(
    weather: WeatherResponse,
    navController: NavController
) {
    val context = LocalContext.current
    val iconCode = weather.weather.firstOrNull()?.main?.lowercase() ?: "clear"
    val currentHour = LocalDateTime.now(ZoneId.systemDefault()).hour
    val isNight = currentHour < 6 || currentHour > 18
    val iconName = "${iconCode}_${if (isNight) "night" else "daytime"}"
    val iconResId = context.resources.getIdentifier(iconName, "drawable", context.packageName)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                val lat = weather.coord.lat
                val lon = weather.coord.lon
                navController.navigate("detail/$lat/$lon")
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${weather.name}, ${weather.sys.country}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = weather.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() }
                    ?: "",
                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (iconResId != 0) {
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Text(
                    text = "${weather.main.temp.toInt()}°C",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${stringResource(id = R.string.humidity_label)}: ${weather.main.humidity}%",
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = "${stringResource(id = R.string.pressure_label)}: ${weather.main.pressure} hPa",
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = "${stringResource(id = R.string.wind_label)}: ${weather.wind.speed} m/s",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
