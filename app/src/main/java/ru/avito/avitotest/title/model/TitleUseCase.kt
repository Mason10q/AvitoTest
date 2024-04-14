package ru.avito.avitotest.title.model

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.avito.avitotest.title.model.entities.Poster
import ru.avito.avitotest.title.model.entities.Review
import ru.avito.avitotest.title.model.entities.Title

interface TitleUseCase {

    fun getTitleById(id: Int): Single<Title>
    fun getReviewFLowByTitleId(id: Int): Flow<PagingData<Review>>
    fun getPostersBuTitleId(id: Int): Single<List<Poster>>

}