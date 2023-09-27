package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Location

data class LocationDto(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
) : DataMapper<Location> {
    override fun toDomain(): Location {
        return Location(
            country = country,
            lat = lat,
            localtime = localtime,
            localtime_epoch = localtime_epoch,
            lon= lon,
            name = name,
            region = region,
            tz_id = tz_id
        )
    }
}