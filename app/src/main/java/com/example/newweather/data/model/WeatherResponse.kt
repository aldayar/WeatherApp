package com.example.newweather.data.model

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)