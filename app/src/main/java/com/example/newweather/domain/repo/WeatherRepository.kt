package com.example.newweather.domain.repo

import com.example.newweather.domain.model.WeatherResponse
import com.example.newweather.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherData(location: String): Flow<Resource<WeatherResponse>>
}