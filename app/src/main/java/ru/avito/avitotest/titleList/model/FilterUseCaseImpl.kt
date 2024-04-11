package ru.avito.avitotest.titleList.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.network.dtos.FilterDto
import ru.avito.avitotest.titleList.data.TitleListRepository
import ru.avito.avitotest.titleList.model.entities.Filter
import ru.avito.avitotest.network.FilterTypes
import javax.inject.Inject

class FilterUseCaseImpl @Inject constructor(
    private val repository: TitleListRepository,
    private val filterMapper: Mapper<String, FilterDto>
): FilterUseCase {
    override fun getAllGenres(): Single<Filter> = repository.getAllGenres()
        .observeOn(AndroidSchedulers.mainThread())
        .map { Filter(FilterTypes.GENRES.title, FilterTypes.GENRES.serverName, filterMapper.map(it)) }


    override fun getAllCountries(): Single<Filter> = repository.getAllCountries()
        .observeOn(AndroidSchedulers.mainThread())
        .map { Filter(FilterTypes.COUNTRIES.title, FilterTypes.COUNTRIES.serverName, filterMapper.map(it)) }

    override fun getAllTypes(): Single<Filter> = repository.getAllTypes()
        .observeOn(AndroidSchedulers.mainThread())
        .map { Filter(FilterTypes.TYPES.title, FilterTypes.TYPES.serverName, filterMapper.map(it)) }

    override fun getAllFilters(): Single<List<Filter>> = Single.zip(
        getAllGenres(),
        getAllCountries(),
        getAllTypes()
    ) { genres, countries, types ->
        listOf(genres, countries, types)
    }
}