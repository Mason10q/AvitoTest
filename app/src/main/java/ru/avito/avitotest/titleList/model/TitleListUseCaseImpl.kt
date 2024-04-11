package ru.avito.avitotest.titleList.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.titleList.data.TitleListRepository
import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.network.retrofit.ProxyRetrofitQueryMap
import javax.inject.Inject

class TitleListUseCaseImpl @Inject constructor(
    private val repository: TitleListRepository,
    private val titleMapper: Mapper<Title, TitleDto>
) : TitleListUseCase {

    override fun getTitlesPage(
        pageNum: Int,
        filters: Map<String, List<String>>
    ) = repository.getTitlesPage(pageNum, ProxyRetrofitQueryMap(filters))
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            titleMapper.map(it.titles ?: throw NullPointerException("Titles is null"))
        }

    override fun search(query: String, filters: Map<String, List<String>>): Single<List<Title>> =
        repository.search(query, ProxyRetrofitQueryMap(filters))
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                titleMapper.map(it.titles ?: throw NullPointerException("Titles is null"))
            }

}