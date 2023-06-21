package com.example.newweather.data.remote

import com.example.newweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("currentConditions/v1")
     fun getCurrentWeather(
        @Query("location") location: String,
        @Query("key") apiKey: String,
    ): Call<List<WeatherResponse>>
}
