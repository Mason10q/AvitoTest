package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name")
    val name: String = ""
)