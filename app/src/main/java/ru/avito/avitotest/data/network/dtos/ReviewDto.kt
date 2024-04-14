package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ReviewDto(
    @SerializedName("review")
    val review: String?,
    @SerializedName("author")
    val author: String?
)