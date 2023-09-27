package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Astro

data class AstroDto(
    val is_moon_up: Int,
    val is_sun_up: Int,
    val moon_illumination: String,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
) : DataMapper<Astro> {
    override fun toDomain(): Astro {
        return Astro(
            is_moon_up = is_moon_up,
            is_sun_up = is_sun_up,
            moon_illumination = moon_illumination,
            moon_phase = moon_phase,
            moonrise = moonrise,
            moonset = moonset,
            sunrise = sunrise,
            sunset = sunset
        )
    }
}