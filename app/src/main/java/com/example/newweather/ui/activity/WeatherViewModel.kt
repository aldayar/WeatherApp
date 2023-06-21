package com.example.newweather.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newweather.core.UIState
import com.example.newweather.data.model.WeatherResponse
import com.example.newweather.data.remote.WeatherRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    val weatherLiveData = MutableLiveData<UIState<WeatherResponse>>()
    fun getCurrentWeather(location: String){
        weatherLiveData.value = UIState.Loading()
        weatherRepository.getWeather(location){ weatherResponce, errorMsg->
            if (errorMsg == null){
                weatherLiveData.value = UIState.Success(weatherResponce)
            }else{
                weatherLiveData.value = UIState.Error(errorMsg)
            }

        }
    }

}