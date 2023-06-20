package com.example.newweather.data.remote

import com.example.newweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current")
    fun getCurrentWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String,
    ): Call<WeatherResponse>
}
