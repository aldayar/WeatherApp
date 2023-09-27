package com.example.newweather.domain.model

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)