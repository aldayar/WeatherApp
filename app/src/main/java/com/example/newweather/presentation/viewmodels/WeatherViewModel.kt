package com.example.newweather.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newweather.domain.model.WeatherResponse
import com.example.newweather.domain.resource.Resource
import com.example.newweather.domain.usecases.GetWeatherUseCase
import com.example.newweather.presentation.core.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) :
    ViewModel() {
    private val _weatherData = MutableStateFlow<UIState<WeatherResponse>>(UIState.Empty())
    val weatherData: StateFlow<UIState<WeatherResponse>> = _weatherData

    fun getWeather(location: String) {
        viewModelScope.launch {
            getWeatherUseCase.excecute(location = location).collect { res ->
                when (res) {
                    is Resource.Loading -> _weatherData.value = UIState.Loading()
                    is Resource.Error -> _weatherData.value = UIState.Error(res.msg.toString())
                    is Resource.Success -> _weatherData.value = UIState.Success(res.data)
                }
            }
        }
    }
}