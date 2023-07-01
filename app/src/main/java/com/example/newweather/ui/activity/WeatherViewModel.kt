package com.example.newweather.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newweather.core.UIState
import com.example.newweather.data.model.WeatherResponse
import com.example.newweather.data.remote.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository) :
    ViewModel() {

    var weatherLiveData = MutableLiveData<UIState<WeatherResponse>>()

    fun getCurrentWeather(location: String) {
        weatherLiveData = weatherRepository.getWeather(location)
    }

}