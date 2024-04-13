package ru.avito.avitotest.titleList.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.network.dtos.FilterDto
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.titleList.model.entities.Filter
import ru.avito.avitotest.data.network.FilterTypes
import ru.avito.avitotest.titleList.model.entities.FilterName
import javax.inject.Inject

class FilterUseCaseImpl @Inject constructor(
    private val repository: TitleListRepository
): FilterUseCase {
    override fun getAllGenres(): Single<Filter> = repository.getAllGenres()
        .observeOn(AndroidSchedulers.mainThread())
        .map {filters ->
            Filter(FilterTypes.GENRES.title, filters.map { FilterName(it.name ?: "", FilterTypes.GENRES.serverName) })
        }


    override fun getAllCountries(): Single<Filter> = repository.getAllCountries()
        .observeOn(AndroidSchedulers.mainThread())
        .map { filters ->
            Filter(FilterTypes.COUNTRIES.title, filters.map { FilterName(it.name ?: "", FilterTypes.COUNTRIES.serverName) })
        }

    override fun getAllTypes(): Single<Filter> = repository.getAllTypes()
        .observeOn(AndroidSchedulers.mainThread())
        .map {filters ->
            Filter(FilterTypes.TYPES.title, filters.map { FilterName(it.name ?: "", FilterTypes.TYPES.serverName) })
        }
    override fun getAllFilters(): Single<List<Filter>> = Single.zip(
        getAllGenres(),
        getAllCountries(),
        getAllTypes()
    ) { genres, countries, types ->
        listOf(genres, countries, types)
    }
}