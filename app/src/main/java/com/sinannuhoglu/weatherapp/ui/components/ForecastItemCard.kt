package com.sinannuhoglu.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinannuhoglu.weatherapp.model.ForecastItem
import com.sinannuhoglu.weatherapp.util.formatHourFromDateTime
import com.sinannuhoglu.weatherapp.util.getDrawableResourceName

@Composable
fun ForecastItemCard(forecastItem: ForecastItem) {
    val context = LocalContext.current
    val iconName = getDrawableResourceName(
        forecastItem.weather.firstOrNull()?.main, forecastItem.dtTxt
    )
    val iconResId = remember(iconName) {
        context.resources.getIdentifier(iconName, "drawable", context.packageName)
    }

    Column(
        modifier = Modifier
            .width(90.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = Color.White.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (iconResId != 0) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }

        Text(
            text = formatHourFromDateTime(forecastItem.dtTxt),
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White
        )

        Text(
            text = "${forecastItem.main.temp.toInt()}°C", fontSize = 13.sp, color = Color.White
        )

        val descriptionParts =
            forecastItem.weather.firstOrNull()?.description?.split(" ") ?: listOf("")

        Text(
            text = descriptionParts.firstOrNull().orEmpty(),
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        if (descriptionParts.size > 1) {
            Text(
                text = descriptionParts.drop(1).joinToString(" "),
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
