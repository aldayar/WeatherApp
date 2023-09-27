package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Forecast
import com.example.newweather.domain.model.Forecastday

data class ForecastDto(
    val forecastday: List<Forecastday>
): DataMapper<Forecast> {
    override fun toDomain() = Forecast (forecastday = forecastday)
}