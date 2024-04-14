package ru.avito.avitotest.titleList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import ru.avito.avitotest.titleList.model.FilterUseCase
import ru.avito.avitotest.titleList.model.TitleListUseCase
import ru.avito.avitotest.titleList.model.entities.Filter
import ru.avito.avitotest.titleList.model.entities.Title
import javax.inject.Inject

class TitleListViewModel @Inject constructor(
    private val titleUseCase: TitleListUseCase,
    private val filterUseCase: FilterUseCase
): ViewModel() {

    private val _filters = MutableLiveData<List<Filter>>()
    val filters: LiveData<List<Filter>> = _filters

    private val filtersMap = HashMap<String, MutableList<String>>()

    private val rangeMap = HashMap<String, List<Float>>()

    fun getTitlePageFlow() = titleUseCase.getTitlesPage(filtersMap)
        .cachedIn(viewModelScope)

    fun getAllFilters() = filterUseCase.getAllFilters()
        .subscribe({filters ->
           _filters.postValue(filters)
        }, {})

    fun search(query: String) = titleUseCase.search(query, filtersMap)
        .cachedIn(viewModelScope)


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

    fun addRangeFilter(key: String, values: List<Float>) {
        rangeMap[key] = values
        filtersMap[key] = mutableListOf("${values[0]}-${values[1]}")
    }

    fun getFilterMap(): Map<String, List<String>> = filtersMap

    fun getRangeMap(): Map<String, List<Float>> = rangeMap
}