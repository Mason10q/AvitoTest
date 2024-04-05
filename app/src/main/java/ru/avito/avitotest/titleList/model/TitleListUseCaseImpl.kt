package ru.avito.avitotest.titleList.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.titleList.data.TitleListRepository
import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.network.dtos.TitleDto
import javax.inject.Inject

class TitleListUseCaseImpl @Inject constructor(
    private val repository: TitleListRepository,
    private val titleMapper: Mapper<Title, TitleDto>
) : TitleListUseCase {

    override fun getTitlesPage(pageNum: Int): Single<List<Title>> = repository.getTitlesPage(pageNum)
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            titleMapper.map(it.titles ?: throw NullPointerException("Titles is null"))
        }

}