package ru.avito.avitotest.titleList.data

import android.util.Log
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.avito.avitotest.network.KinopoiskApi
import ru.avito.avitotest.network.dtos.DocsDto
import ru.avito.avitotest.network.dtos.FilterDto
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.network.retrofit.ProxyRetrofitQueryMap
import javax.inject.Inject

class TitleListRepositoryImpl @Inject constructor(private val api: KinopoiskApi) :
    TitleListRepository {

    override fun getTitlesPage(pageNum: Int, filters: ProxyRetrofitQueryMap) =
        api.getTitlesPage(filters, getSerializedNames(TitleDto::class.java))
            .subscribeOn(Schedulers.io())


    override fun getAllGenres(): Single<List<FilterDto>> = api.getAllGenres()
        .subscribeOn(Schedulers.io())

    override fun getAllCountries(): Single<List<FilterDto>> = api.getAllCountries()
        .subscribeOn(Schedulers.io())

    override fun getAllTypes(): Single<List<FilterDto>> = api.getAllTypes()
        .subscribeOn(Schedulers.io())

    override fun search(query: String, filters: ProxyRetrofitQueryMap): Single<DocsDto> =
        api.search(query, getSerializedNames(TitleDto::class.java))
            .subscribeOn(Schedulers.io())

    private fun getSerializedNames(dtoClass: Class<*>): List<String> = ArrayList<String>().apply {
        dtoClass.declaredFields.forEach { field ->
            field.getAnnotation(SerializedName::class.java)?.value?.let { this.add(it) }
        }
    }

}