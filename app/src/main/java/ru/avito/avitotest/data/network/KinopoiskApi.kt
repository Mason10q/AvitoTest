package ru.avito.avitotest.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.avito.avitotest.data.network.dtos.DocsDto
import ru.avito.avitotest.data.network.dtos.FilterDto
import ru.avito.avitotest.data.network.retrofit.EndpointUrl
import ru.avito.avitotest.data.network.retrofit.ProxyRetrofitQueryMap
import androidx.annotation.IntRange
import retrofit2.http.Path
import ru.avito.avitotest.data.network.dtos.PosterDto
import ru.avito.avitotest.data.network.dtos.ReviewDto
import ru.avito.avitotest.data.network.dtos.TitleDto


@EndpointUrl("https://api.kinopoisk.dev/")
interface KinopoiskApi {
    @GET("v1.4/movie")
    fun getTitlesPage(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE,
        @QueryMap filters: ProxyRetrofitQueryMap? = null,
        @Query("selectFields") fields: List<String>? = null
    ): Single<DocsDto<TitleDto>>

    @GET("v1/movie/possible-values-by-field")
    fun getFilters(@Query("field") filterType: String): Single<List<FilterDto>>

    @GET("v1.4/movie/search")
    fun search(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("query") query: String,
    ): Single<DocsDto<TitleDto>>

    @GET("v1.4/movie/{id}")
    fun getTitleById(@Path("id") id: Int): Single<TitleDto>

    @GET("v1.4/review")
    fun getReviewsByTitleId(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("limit") @IntRange(from = 1, to = 5) pageSize: Int = 5,
        @Query("movieId") id: Int,
        @Query("selectFields") fields: List<String>? = null
    ): Single<DocsDto<ReviewDto>>

    @GET("v1.4/image")
    fun getPostersByTitleId(@Query("movieId") id: List<String>, @Query("selectFields") fields: List<String>? = null): Single<DocsDto<PosterDto>>

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
        const val MAX_PAGE_SIZE = 20
    }

}