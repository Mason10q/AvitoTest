package ru.avito.avitotest.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.avito.avitotest.data.network.dtos.DocsDto
import ru.avito.avitotest.data.network.dtos.FilterDto
import ru.avito.avitotest.data.network.dtos.PosterDto
import ru.avito.avitotest.data.network.dtos.ReviewDto
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.data.network.retrofit.ProxyRetrofitQueryMap

interface TitleListRepository {

    fun getTitlesPage(pageNum: Int, filters: ProxyRetrofitQueryMap): Single<DocsDto<TitleDto>>

    fun getAllGenres(): Single<List<FilterDto>>

    fun getAllCountries(): Single<List<FilterDto>>

    fun getAllTypes(): Single<List<FilterDto>>

    fun search(pageNum: Int, query: String): Single<DocsDto<TitleDto>>

    fun getTitleById(id: Int): Single<TitleDto>

    fun getReviewsByTitleId(pageNum: Int, id: Int): Single<DocsDto<ReviewDto>>

    fun getPostersByTitleId(id: List<String>): Single<DocsDto<PosterDto>>
}