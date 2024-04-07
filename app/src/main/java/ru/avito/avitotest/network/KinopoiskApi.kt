package ru.avito.avitotest.network

import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.avito.avitotest.network.dtos.DocsDto
import ru.avito.avitotest.network.dtos.FilterDto
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.network.retrofit.EndpointUrl


@EndpointUrl("https://api.kinopoisk.dev/")
interface KinopoiskApi {

    private fun getSerializedNames(dtoClass: Class<*>): List<String> = ArrayList<String>().apply {
        dtoClass.declaredFields.forEach { field ->
            field.getAnnotation(SerializedName::class.java)?.value?.let { this.add(it) }
        }
    }

    private val titlePreviewFields: List<String>
        get() = getSerializedNames(TitleDto::class.java)

    @GET("v1.4/movie?page=1&limit=10")
    fun getTitlesPage(@Query("selectFields") fields: List<String> = titlePreviewFields): Single<DocsDto>

    @GET("v1/movie/possible-values-by-field?field=genres.name")
    fun getAllGenres(): Single<List<FilterDto>>

    @GET("v1/movie/possible-values-by-field?field=countries.name")
    fun getAllCountries(): Single<List<FilterDto>>

    @GET("v1/movie/possible-values-by-field?field=type")
    fun getAllTypes(): Single<List<FilterDto>>

    @GET("v1.4/movie/search?page=1&limit=10")
    fun search(
        @Query("query") query: String,
        @Query("selectFields") fields: List<String> = titlePreviewFields
        ): Single<DocsDto>

}