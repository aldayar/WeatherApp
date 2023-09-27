package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Forecastday

data class ForecastdayDto(
    val astro: AstroDto,
    val date: String,
    val date_epoch: Int,
    val day: DayDto,
    val hour: List<HourDto>
) : DataMapper<Forecastday> {
    override fun toDomain(): Forecastday {
        return Forecastday(
            astro = astro.toDomain(),
            date = date,
            date_epoch = date_epoch,
            hour = hour.map { it.toDomain() },
            day = day.toDomain()
        )

    }
}