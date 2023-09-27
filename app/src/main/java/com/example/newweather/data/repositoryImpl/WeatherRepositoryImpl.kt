package com.example.newweather.data.repositoryImpl

import com.example.newweather.data.remote.ApiService
import com.example.newweather.domain.model.WeatherResponse
import com.example.newweather.domain.repo.WeatherRepository
import com.example.newweather.domain.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    WeatherRepository {
    override suspend fun getWeatherData(location: String): Flow<Resource<WeatherResponse>> =
        flow {
            emit(Resource.Loading())
            val response = apiService.getCurrentWeather(location = location)
            if (response.isSuccessful) {
                val imageModel = response.body()
                if (imageModel != null) {
                    emit(Resource.Success(imageModel))
                } else {
                    emit(Resource.Error(response.message().toString() ))
                }
            } else {
                emit(Resource.Error("HTTP Error: ${response.code()}"))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(e.message.toString()))
        }
}