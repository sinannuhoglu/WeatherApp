
# WeatherApp

WeatherApp is an Android application that displays current weather data and 5-day forecasts based on the user's location or a selected point on the map. At launch, the app retrieves the user's coordinates and shows weather information using OpenWeatherMap API. Users can also select different locations via an interactive Google Maps interface. The project is built using **Jetpack Compose**, **MVVM architecture**, **Hilt**, **Retrofit**, **Google Maps** and other modern Android development tools.

---

## Screenshots

| Splash Screen | Home Screen | Detail Screen | Map Screen |
|---------------|-------------|---------------|-------------|
| <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/91096ff1-a31c-4046-bb6c-1147fa81a6eb" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/7cca95f7-d4ae-4c87-a9cb-092be2ebe3f1" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/b07e3a78-fd95-4e16-824b-6575f038f5ae" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/dda7fea8-197e-430a-9b42-379ae4f2cbc8" /> |

---

## Project Structure

```
com.sinannuhoglu.weatherapp
├── di                     # Hilt DI Module (AppModule)
├── model                  # Weather & Forecast data models
├── navigation
│   └── NavGraph.kt        # Compose Navigation Graph and routes
├── network
│   ├── WeatherApiService  # Retrofit API interface
│   └── WeatherRepository  # API call wrapper class
├── ui                     # UI Layer
│   ├── components         # Reusable Composable UI components (cards, buttons, etc.)
│   ├── detail             # DetailScreen (5-day forecast, hourly forecast, etc.)
│   ├── home               # HomeScreen (current weather and nearby cities)
│   ├── map                # MapScreen (Google Maps and location picker)
│   └── splash             # SplashScreen (Lottie animation, permission handling)
├── util
│   ├── Constants.kt       # API Key and Base URL
│   ├── CityListProvider   # Predefined cities by country code
│   ├── WeatherIconUtil.kt # Icon selection based on time and weather
│   └── formatHour...      # DateTime formatting helpers
├── MainActivity.kt        # App entry point
└── WeatherApp.kt          # Hilt application class
```

---

## Features

- Real-time weather based on location
- Hourly and 5-day forecasts
- Weather for nearby cities
- Select location via map
- Dynamic day/night icons
- Jetpack Compose UI
- Hilt for Dependency Injection
- Retrofit + Coroutines for API calls

---

## Technologies Used

| Technology             | Description                                |
|------------------------|--------------------------------------------|
| Kotlin                 | Primary language for Android development   |
| Jetpack Compose        | Declarative UI framework                   |
| MVVM Architecture      | Modular and testable architecture pattern  |
| Hilt                   | Dependency Injection framework             |
| Retrofit               | HTTP client for REST APIs                  |
| Gson                   | JSON parsing and conversion                |
| OkHttp                 | HTTP networking                            |
| OpenWeatherMap API     | Weather data provider                      |
| Google Maps Compose    | Map view and location selection            |
| Accompanist Permissions| Permission handling for Compose            |
| Coil                   | Image loading (weather icons)              |
| Lottie Compose         | Animations for splash screen               |
| Coroutines             | Asynchronous programming and API handling  |

