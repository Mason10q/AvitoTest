package ru.avito.avitotest.titleList.data

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.avito.avitotest.network.KinopoiskApi
import ru.avito.avitotest.network.dtos.DocsDto
import ru.avito.avitotest.network.dtos.FilterDto
import javax.inject.Inject

class TitleListRepositoryImpl @Inject constructor(private val api: KinopoiskApi):
    TitleListRepository {
    override fun getTitlesPage(pageNum: Int)= api.getTitlesPage()
            .subscribeOn(Schedulers.io())

    override fun getAllGenres(): Single<List<FilterDto>> = api.getAllGenres()
        .subscribeOn(Schedulers.io())

    override fun getAllCountries(): Single<List<FilterDto>> = api.getAllCountries()
        .subscribeOn(Schedulers.io())

    override fun getAllTypes(): Single<List<FilterDto>> = api.getAllTypes()
        .subscribeOn(Schedulers.io())

    override fun search(query: String): Single<DocsDto> = api.search(query)
        .subscribeOn(Schedulers.io())

}