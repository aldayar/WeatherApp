package com.example.newweather.data.remote

import android.util.Log
import com.example.newweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val apiService: ApiService) {
    fun getWeather(loctioin: String, callback: (WeatherResponse, String?) -> Unit) {
        val call = apiService.getCurrentWeather(loctioin, "7480b0b09f454a4e8a8221810232106")
        call.enqueue(object : Callback<List<WeatherResponse>> {
            override fun onResponse(
                call: Call<List<WeatherResponse>>, response: Response<List<WeatherResponse>>){
                val weatherResponse = response.body()?.get(0)
                if (weatherResponse != null) {
                    callback(weatherResponse, null)
                }
            }

            override fun onFailure(call: Call<List<WeatherResponse>>, t: Throwable) {
                Log.e(t.message.toString(), "ololo" )
            }

        })
    }
}