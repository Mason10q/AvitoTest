package ru.avito.avitotest.data

import android.util.Log
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.avito.avitotest.data.network.KinopoiskApi
import ru.avito.avitotest.data.network.dtos.DocsDto
import ru.avito.avitotest.data.network.dtos.FilterDto
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.data.network.retrofit.ProxyRetrofitQueryMap
import javax.inject.Inject

class TitleListRepositoryImpl @Inject constructor(private val api: KinopoiskApi) :
    TitleListRepository {

    override fun getTitlesPage(pageNum: Int, filters: ProxyRetrofitQueryMap) =
        api.getTitlesPage(page = pageNum, filters = filters, fields = getSerializedNames(TitleDto::class.java))
            .subscribeOn(Schedulers.io())


    override fun getAllGenres(): Single<List<FilterDto>> = api.getFilters("genres.name")
        .subscribeOn(Schedulers.io())

    override fun getAllCountries(): Single<List<FilterDto>> = api.getFilters("countries.name")
        .subscribeOn(Schedulers.io())

    override fun getAllTypes(): Single<List<FilterDto>> = api.getFilters("type")
        .subscribeOn(Schedulers.io())

    override fun search(pageNum: Int, query: String): Single<DocsDto> =
        api.search(page = pageNum, query = query)
            .subscribeOn(Schedulers.io())

    private fun getSerializedNames(dtoClass: Class<*>): List<String> = ArrayList<String>().apply {
        dtoClass.declaredFields.forEach { field ->
            field.getAnnotation(SerializedName::class.java)?.value?.let { this.add(it) }
        }
    }

}