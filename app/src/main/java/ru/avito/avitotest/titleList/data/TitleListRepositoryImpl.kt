package ru.avito.avitotest.titleList.data

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.avito.avitotest.network.KinopoiskApi
import javax.inject.Inject

class TitleListRepositoryImpl @Inject constructor(private val api: KinopoiskApi):
    TitleListRepository {
    override fun getTitlesPage(pageNum: Int) = api.getTitlesPage(pageNum)
        .subscribeOn(Schedulers.io())

}