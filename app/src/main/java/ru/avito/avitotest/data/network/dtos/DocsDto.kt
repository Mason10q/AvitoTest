package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName

data class DocsDto (
    @SerializedName("docs")
    val titles: List<TitleDto>?,
    @SerializedName("total")
    val total: Int?
)