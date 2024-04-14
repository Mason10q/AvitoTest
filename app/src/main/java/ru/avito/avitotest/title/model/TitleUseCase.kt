package ru.avito.avitotest.title.model

import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.title.model.entities.Poster
import ru.avito.avitotest.title.model.entities.Review
import ru.avito.avitotest.title.model.entities.Title

interface TitleUseCase {

    fun getTitleById(id: Int): Single<Title>
    fun getReviewByTitleId(id: Int): Single<List<Review>>
    fun getPostersBuTitleId(id: Int): Single<List<Poster>>

}