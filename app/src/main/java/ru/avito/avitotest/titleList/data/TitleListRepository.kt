package ru.avito.avitotest.titleList.data

import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.network.dtos.TitleDto

interface TitleListRepository {

    fun getTitlesPage(pageNum: Int): Single<List<TitleDto>>

}