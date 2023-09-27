package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.WeatherResponse

data class WeatherResponseDto(
    val current: CurrentDto,
    val forecast: ForecastDto,
    val location: LocationDto
) : DataMapper<WeatherResponse> {
    override fun toDomain(): WeatherResponse {
        return WeatherResponse(
            current = current.toDomain(),
            forecast = forecast.toDomain(),
            location = location.toDomain()
        )
    }
}