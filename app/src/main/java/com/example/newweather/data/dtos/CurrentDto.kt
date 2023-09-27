package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Current

data class CurrentDto(
    val cloud: Int,
    val condition: ConditionDto,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double
) : DataMapper<Current> {
    override fun toDomain(): Current {
        return Current(
            cloud = cloud,
            condition = condition.toDomain(),
            feelslike_c = feelslike_c,
            feelslike_f = feelslike_f,
            gust_kph = gust_kph,
            gust_mph = gust_mph,
            humidity = humidity,
            is_day = is_day,
            last_updated = last_updated,
            last_updated_epoch = last_updated_epoch,
            precip_in = precip_in,
            precip_mm = precip_mm,
            pressure_in = pressure_in,
            pressure_mb = pressure_mb,
            temp_c = temp_c,
            temp_f = temp_f,
            uv = uv,
            vis_km = vis_km,
            vis_miles = vis_miles,
            wind_degree = wind_degree,
            wind_dir = wind_dir,
            wind_kph = wind_kph,
            wind_mph = wind_mph
        )
    }
}