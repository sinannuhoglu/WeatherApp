package com.sinannuhoglu.weatherapp.di

import android.util.Log
import com.sinannuhoglu.weatherapp.network.WeatherApiService
import com.sinannuhoglu.weatherapp.network.WeatherRepository
import com.sinannuhoglu.weatherapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApiService): WeatherRepository {
        Log.d("Hilt", "WeatherRepository provided by Hilt")
        return WeatherRepository(api)
    }
}
