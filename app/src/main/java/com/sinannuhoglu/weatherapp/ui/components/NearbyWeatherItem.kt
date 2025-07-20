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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sinannuhoglu.weatherapp.R

@Composable
fun NearbyWeatherItem(
    cityName: String,
    temp: String,
    humidity: String,
    pressure: String,
    windSpeed: String,
    weatherMain: String,
    lat: Double,
    lon: Double,
    navController: NavController
) {
    val context = LocalContext.current
    val iconName = "${weatherMain.lowercase()}_daytime"
    val iconResId = context.resources.getIdentifier(iconName, "drawable", context.packageName)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            navController.navigate("detail/$lat/$lon")
        }
        .background(
            color = Color.White.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp)
        )
        .padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = cityName, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp
        )

        Text(
            text = weatherMain.replaceFirstChar { it.uppercase() },
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconResId != 0) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = temp, fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${stringResource(id = R.string.humidity_label)}: $humidity",
            color = Color.White,
            fontSize = 13.sp
        )
        Text(
            text = "${stringResource(id = R.string.pressure_label)}: $pressure",
            color = Color.White,
            fontSize = 13.sp
        )
        Text(
            text = "${stringResource(id = R.string.wind_label)}: $windSpeed",
            color = Color.White,
            fontSize = 13.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NearbyWeatherItemPreview() {
    val dummyNavController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        NearbyWeatherItem(
            cityName = "İstanbul",
            temp = "28°C",
            humidity = "60%",
            pressure = "1013 hPa",
            windSpeed = "10 km/h",
            weatherMain = "Clear",
            lat = 41.0082,
            lon = 28.9784,
            navController = dummyNavController
        )
    }
}