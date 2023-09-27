package com.example.newweather.data.remote

import com.example.newweather.BuildConfig.API_KEY
import com.example.newweather.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("key") apiKey: String = API_KEY,
        @Query("days") days: Int = 7
    ): Response<WeatherResponse>
}
