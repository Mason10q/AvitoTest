package ru.avito.avitotest.network.dtos

import com.google.gson.annotations.SerializedName

data class DocsDto (
    @SerializedName("docs")
    val titles: List<TitleDto>
)