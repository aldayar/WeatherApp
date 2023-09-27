package com.example.newweather.domain.resource

sealed class Resource<T>(
    val data: T? = null,
    val msg: T? = null
) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>()
    class Error<T>(message: String) : Resource<T>()
}