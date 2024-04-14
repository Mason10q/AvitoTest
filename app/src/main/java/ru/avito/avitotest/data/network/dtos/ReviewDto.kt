package ru.avito.avitotest.data.network.dtos

import java.time.LocalDateTime

data class ReviewDto(
    val review: String?,
    val author: String?,
    val createdAt: LocalDateTime?
)