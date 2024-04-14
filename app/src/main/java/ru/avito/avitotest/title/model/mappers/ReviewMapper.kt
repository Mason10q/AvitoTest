package ru.avito.avitotest.title.model.mappers

import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.network.dtos.ReviewDto
import ru.avito.avitotest.title.model.entities.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor(): Mapper<Review, ReviewDto> {

    override fun map(item: ReviewDto): Review = Review(
        item.review ?: "",
        item.author ?: ""
    )
}