package ru.avito.avitotest.data

import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.data.network.dtos.DocsDto
import ru.avito.avitotest.data.network.dtos.FilterDto
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.data.network.retrofit.ProxyRetrofitQueryMap

interface TitleListRepository {

    fun getTitlesPage(pageNum: Int, filters: ProxyRetrofitQueryMap): Single<DocsDto>

    fun getAllGenres(): Single<List<FilterDto>>

    fun getAllCountries(): Single<List<FilterDto>>

    fun getAllTypes(): Single<List<FilterDto>>

    fun search(pageNum: Int, query: String): Single<DocsDto>
}