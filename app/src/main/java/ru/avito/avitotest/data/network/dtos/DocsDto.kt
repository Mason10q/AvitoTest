package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName

data class DocsDto <DTO> (
    @SerializedName("docs")
    val data: List<DTO>?,
    @SerializedName("total")
    val total: Int?
)