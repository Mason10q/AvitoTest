package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName


data class TitleDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("alternativeName")
    val alternativeName: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("movieLength")
    val length: Int?,
    @SerializedName("poster")
    val posterDto: PosterDto?,
    @SerializedName("countries")
    val counties: List<CountryDto>?,
    @SerializedName("genres")
    val genres: List<GenreDto>?,
    @SerializedName("rating")
    val rating: RatingDto?
)