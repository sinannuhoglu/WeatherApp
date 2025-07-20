package com.sinannuhoglu.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.sinannuhoglu.weatherapp.ui.map.MapScreen
import com.sinannuhoglu.weatherapp.ui.detail.DetailScreen
import com.sinannuhoglu.weatherapp.ui.home.HomeScreen
import com.sinannuhoglu.weatherapp.ui.splash.SplashScreen

@Composable
fun WeatherNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                onPermissionGranted = { lat, lon ->
                    navController.navigate("home/$lat/$lon") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "home/{lat}/{lon}",
            arguments = listOf(
                navArgument("lat") { type = NavType.StringType },
                navArgument("lon") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lon = backStackEntry.arguments?.getString("lon")?.toDoubleOrNull() ?: 0.0
            HomeScreen(lat = lat, lon = lon, navController = navController)
        }

        composable(
            route = "detail/{lat}/{lon}",
            arguments = listOf(
                navArgument("lat") { type = NavType.StringType },
                navArgument("lon") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lon = backStackEntry.arguments?.getString("lon")?.toDoubleOrNull() ?: 0.0
            DetailScreen(lat = lat, lon = lon, navController = navController)
        }

        composable(
            route = "map/{lat}/{lon}",
            arguments = listOf(
                navArgument("lat") { type = NavType.StringType },
                navArgument("lon") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lon = backStackEntry.arguments?.getString("lon")?.toDoubleOrNull() ?: 0.0
            MapScreen(lat = lat, lon = lon, navController = navController)
        }

    }
}
