package ru.avito.avitotest.titleList.model.entities


data class Title(
    val id: Int,
    val name: String,
    val alternativeName: String,
    val year: Int,
    val country: String,
    val genre: String,
    val rating: Double,
    val posterPreviewUrl: String
)