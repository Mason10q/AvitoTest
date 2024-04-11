package ru.avito.avitotest.titleList.model.entities

data class RangeFilter(
    val title: String,
    val serverName: String,
    val valueFrom: Float,
    val valueTo: Float,
    val stepSize: Float,
    val startValues: List<Float> = listOf(valueFrom, valueTo)
)