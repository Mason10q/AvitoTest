package ru.avito.avitotest.titleList.model

import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.data.network.dtos.FilterDto
import ru.avito.avitotest.titleList.model.entities.Filter

interface FilterUseCase {

    fun getAllGenres(): Single<Filter>

    fun getAllCountries(): Single<Filter>

    fun getAllTypes(): Single<Filter>

    fun getAllFilters(): Single<List<Filter>>

}