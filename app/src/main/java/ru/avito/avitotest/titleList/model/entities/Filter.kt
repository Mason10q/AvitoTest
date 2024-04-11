package ru.avito.avitotest.titleList.model.entities

data class Filter (
    val title: String,
    val serverName: String,
    val filterNames: List<String>
)