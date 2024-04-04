package ru.avito.avitotest.network.dtos

import com.google.gson.annotations.SerializedName


data class TitleDto(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("alternativeName")
    val alternativeName: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("year")
    val year: Int? = 1970,
    @SerializedName("movieLength")
    val length: Int? = 0,
    @SerializedName("poster")
    val posterDto: PosterDto?,
    @SerializedName("countries")
    val counties: List<CountryDto>?
)