package com.example.newweather.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newweather.core.UIState
import com.example.newweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val apiService: ApiService) {
    fun getWeather(location: String): MutableLiveData<UIState<WeatherResponse>> {
        val livedata = MutableLiveData<UIState<WeatherResponse>>()
        livedata.value = UIState.Loading()
        val call = apiService.getCurrentWeather(location = location)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>, response: Response<WeatherResponse>
            ) {
                if (response.body() != null) {
                    livedata.value = UIState.Success(response.body())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("ololo", t.message.toString())
                livedata.value = UIState.Error(t.message)
            }
        })
        return livedata
    }

}