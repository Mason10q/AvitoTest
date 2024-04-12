package ru.avito.avitotest.titleList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.avito.avitotest.titleList.model.FilterUseCase
import ru.avito.avitotest.titleList.model.TitleListUseCase
import ru.avito.avitotest.titleList.model.entities.Filter
import ru.avito.avitotest.titleList.model.entities.Title
import javax.inject.Inject

class TitleListViewModel @Inject constructor(
    private val titleUseCase: TitleListUseCase,
    private val filterUseCase: FilterUseCase
): ViewModel() {

    private val _titles = MutableLiveData<List<Title>>()
    val titles: LiveData<List<Title>> = _titles

    private val _filters = MutableLiveData<List<Filter>>()
    val filters: LiveData<List<Filter>> = _filters

    private val filtersMap = HashMap<String, MutableList<String>>()

    private val currentPage = 1

    fun getTitlesPage() = titleUseCase.getTitlesPage(currentPage, filtersMap)
        .subscribe({ titles ->
            _titles.postValue(titles)
        }, {})

    fun getAllFilters() = filterUseCase.getAllFilters()
        .subscribe({filters ->
           _filters.postValue(filters)
        }, {})

    fun search(query: String) = titleUseCase.search(query, filtersMap)
        .subscribe({titles ->
           _titles.postValue(titles)
        }, {})


    fun addFilter(key: String, value: String) {
        if(filtersMap.containsKey(key)){
            filtersMap[key]?.add(value)
        } else {
            filtersMap[key] = mutableListOf(value)
        }
    }

    fun deleteFilter(key: String, value: String) {
        filtersMap[key]?.remove(value)
    }

    fun addRangeFilter(key: String, value: String) {
        filtersMap[key] = mutableListOf(value)
    }

    fun getFilterMap(): Map<String, List<String>> = filtersMap
}