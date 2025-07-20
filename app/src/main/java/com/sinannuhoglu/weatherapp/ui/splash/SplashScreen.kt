package com.sinannuhoglu.weatherapp.ui.splash

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.google.accompanist.permissions.*
import com.sinannuhoglu.weatherapp.R
import com.sinannuhoglu.weatherapp.ui.components.RectGradientButton

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(
    onPermissionGranted: (Double, Double) -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel()

) {
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("splash_animation.json"))
    val animationState =
        animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0b327a), Color(0xFF134bb2), Color(0xFF1148b0)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.splash_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.splash_subtitle),
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            LottieAnimation(
                composition = composition,
                progress = { animationState.progress },
                modifier = Modifier.size(280.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            RectGradientButton(
                text = stringResource(R.string.continue_button),
                onClick = { locationPermissionState.launchPermissionRequest() }
            )
        }
    }

    LaunchedEffect(locationPermissionState.status) {
        if (locationPermissionState.status.isGranted) {
            try {
                val locationManager =
                    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val location: Location? =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                location?.let {
                    Log.d("Splash", "Lat: ${it.latitude}, Lon: ${it.longitude}")
                    splashViewModel.setPermissionGranted(true)
                    onPermissionGranted(it.latitude, it.longitude)
                } ?: Log.e("Splash", "Konum alınamadı!")
            } catch (e: SecurityException) {
                Log.e("Splash", "Konum izni hatası: ${e.message}")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onPermissionGranted = { _, _ -> })
}

