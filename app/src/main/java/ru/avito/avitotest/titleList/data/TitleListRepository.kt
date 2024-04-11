package ru.avito.avitotest.titleList.data

import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.network.dtos.DocsDto
import ru.avito.avitotest.network.dtos.FilterDto
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.network.retrofit.ProxyRetrofitQueryMap

interface TitleListRepository {

    fun getTitlesPage(pageNum: Int, filters: ProxyRetrofitQueryMap): Single<DocsDto>

    fun getAllGenres(): Single<List<FilterDto>>

    fun getAllCountries(): Single<List<FilterDto>>

    fun getAllTypes(): Single<List<FilterDto>>

    fun search(query: String, filters: ProxyRetrofitQueryMap): Single<DocsDto>
}