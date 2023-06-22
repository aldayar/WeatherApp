package com.example.newweather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val currentWeather: CurrentWeather
)
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val tz_id: String,
    val localtime_epoch: Int,
    val localTime: Int,
)
data class CurrentWeather(
    val epochTime: Long,
    val last_updated_epoch: Int,
    val last_updated: String,
    val temp_c: Int,
    val temp_f: Int,
    val is_day:Int,
    val condition: Condition,
    val wind_mph: Float,
    val wind_kph: Float,
    val wind_degree: Float,
    val wind_dir: String,
    val pressure_mb: Float,
    val pressure_in: Float,
    val humidity: Int,
    val cloud: Int
)
data class Condition(
    val text:String,
    val icon:String,
    val code: Int
)