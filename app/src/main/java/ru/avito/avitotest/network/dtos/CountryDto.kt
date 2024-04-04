package ru.avito.avitotest.network.dtos

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name")
    val name: String = ""
)