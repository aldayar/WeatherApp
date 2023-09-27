package com.example.newweather.domain.usecases

import com.example.newweather.domain.repo.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repo: WeatherRepository) {
    suspend fun excecute(location: String) =
        repo.getWeatherData(location = location)
}