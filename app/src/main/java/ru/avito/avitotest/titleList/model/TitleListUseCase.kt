package ru.avito.avitotest.titleList.model

import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.titleList.model.entities.Title

interface TitleListUseCase {

    fun getTitlesPage(pageNum: Int): Single<List<Title>>

    fun search(query: String): Single<List<Title>>

}