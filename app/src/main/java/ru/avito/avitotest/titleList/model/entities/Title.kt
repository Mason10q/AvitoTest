package ru.avito.avitotest.titleList.model.entities


data class Title(
    val name: String,
    val alternativeName: String,
    val year: Int,
    val country: String,
    val genre: String,
    val rating: Double
)