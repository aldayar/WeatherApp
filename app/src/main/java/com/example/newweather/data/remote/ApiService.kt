package com.example.newweather.data.remote

import com.example.newweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json")
     fun getCurrentWeather(
        @Query("q") location: String,
        @Query("key") apiKey: String = "7480b0b09f454a4e8a8221810232106",
        @Query("days") days: Int = 7
    ): Call<WeatherResponse>
}
