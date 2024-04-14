package ru.avito.avitotest.title.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.data.network.dtos.PosterDto
import ru.avito.avitotest.data.network.dtos.ReviewDto
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.title.model.entities.Poster
import ru.avito.avitotest.title.model.entities.Review
import ru.avito.avitotest.title.model.entities.Title
import javax.inject.Inject

class TitleUseCaseImpl @Inject constructor(
    private val repository: TitleListRepository,
    private val titleMapper: Mapper<Title, TitleDto>,
    private val reviewMapper: Mapper<Review, ReviewDto>,
    private val posterMapper: Mapper<Poster, PosterDto>
): TitleUseCase {

    override fun getTitleById(id: Int): Single<Title> = repository.getTitleById(id)
        .observeOn(AndroidSchedulers.mainThread())
        .map(titleMapper::map)

    override fun getReviewByTitleId(id: Int): Single<List<Review>> = repository.getReviewsByTitleId(id)
        .observeOn(AndroidSchedulers.mainThread())
        .map{ reviewMapper.map(it.data ?: emptyList()) }

    override fun getPostersBuTitleId(id: Int): Single<List<Poster>> = repository.getPostersByTitleId(listOf(id.toString()))
        .observeOn(AndroidSchedulers.mainThread())
        .map { posterMapper.map(it.data ?: emptyList()) }


}