package ru.avito.avitotest.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.network.retrofit.EndpointUrl

@EndpointUrl("https://api.kinopoisk.dev/v1.4/")
interface KinopoiskApi {

    @GET("movie")
    fun getTitlesPage(pageNum: Int) : Single<List<TitleDto>>

}