package com.sinannuhoglu.weatherapp.ui.map

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.sinannuhoglu.weatherapp.R
import com.sinannuhoglu.weatherapp.ui.components.MapRectGradientButton

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    lat: Double, lon: Double, navController: NavController
) {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val defaultLatLng = remember { LatLng(lat, lon) }
    var selectedLocation by remember { mutableStateOf(defaultLatLng) }

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(Unit) {
        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(defaultLatLng, 10f))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                selectedLocation = latLng
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
            },
            properties = MapProperties(
                isMyLocationEnabled = permissionState.status.isGranted
            )
        ) {
            Marker(
                state = MarkerState(position = selectedLocation),
                title = stringResource(R.string.map_marker_title)
            )

        }

        MapRectGradientButton(
            text = stringResource(R.string.map_button_text), onClick = {
                navController.navigate("home/${selectedLocation.latitude}/${selectedLocation.longitude}")
            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}
