package com.example.newweather.data.dtos

import com.example.newweather.data.mapper.DataMapper
import com.example.newweather.domain.model.Condition

data class ConditionDto(
    val code: Int,
    val icon: String,
    val text: String
) : DataMapper<Condition> {
    override fun toDomain(): Condition {
        return Condition(
            code = code, icon = icon, text = text
        )
    }
}