package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Hour

data class HourDto(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: ConditionDto,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val time_epoch: Int,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val will_it_rain: Int,
    val will_it_snow: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
) : DataMapper<Hour> {
    override fun toDomain(): Hour {
        return Hour(
            chance_of_rain = chance_of_rain,
            chance_of_snow = chance_of_snow,
            cloud =cloud,
            condition = condition.toDomain(),
            dewpoint_c = dewpoint_c,
            dewpoint_f = dewpoint_f,
            feelslike_c = feelslike_c,
            feelslike_f = feelslike_f,
            gust_kph = gust_kph,
            gust_mph = gust_mph,
            heatindex_c = heatindex_c,
            heatindex_f = heatindex_f,
            humidity = humidity,
            is_day = is_day,
            precip_in = precip_in,
            precip_mm = precip_mm,
            pressure_in = pressure_in,
            pressure_mb = pressure_mb,
            temp_c = temp_c,
            temp_f = temp_f,
            time = time,
            time_epoch = time_epoch,
            uv = uv,
            vis_km = vis_km,
            vis_miles = vis_miles,
            will_it_rain = will_it_rain,
            will_it_snow = will_it_snow,
            wind_degree = wind_degree,
            wind_dir = wind_dir,
            wind_kph = wind_kph,
            wind_mph = wind_mph,
            windchill_c = windchill_c,
            windchill_f = windchill_f
        )
    }
}