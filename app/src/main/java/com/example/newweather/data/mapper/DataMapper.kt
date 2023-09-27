package com.example.newweather.data.mapper

interface DataMapper<T> {
    fun toDomain(): T
}