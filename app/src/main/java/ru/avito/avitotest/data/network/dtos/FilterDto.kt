package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName

class FilterDto (
    @SerializedName("name")
    val name: String?
)