package com.example.newweather.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newweather.R
import com.example.newweather.data.model.CurrentWeather
import com.example.newweather.data.model.WeatherResponse
import com.example.newweather.data.remote.WeatherApiClient
import com.example.newweather.databinding.ActivityMainBinding
import com.example.newweather.databinding.WetherInfoItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherApi = WeatherApiClient.create()
        val city = "Bishkek"
        val apiKey = "my_api_key"

        val call = weatherApi.getCurrentWeather(city, apiKey)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful){
                    val weatherResponse = response.body()
                    if (weatherResponse!= null){
                        val currentWeather = weatherResponse.currentWeather
                        showWeather(currentWeather)
                    }
                }

            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun showWeather(currentWeather: CurrentWeather){
        val temperatureText = "${currentWeather.temp_c}°C"
        val humidityText = "Humidity: ${currentWeather.humidity}%"
        val pressureText = "Pressure: ${currentWeather.pressure_mb} mBar"
        val windSpeedText = "Wind Speed: ${currentWeather.wind_kph} km/h"


        binding.tvDagre.text = temperatureText
        binding.tvHumidity.text = humidityText
        binding.tvPressure.text = pressureText
        binding.tvWindSpeed.text = windSpeedText
    }
}