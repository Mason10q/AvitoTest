package ru.avito.avitotest.titleList.ui

import android.util.Log
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

    private val currentPage = 1

    fun getTitlesPage() = titleUseCase.getTitlesPage(currentPage)
        .subscribe({ titles ->
            _titles.postValue(titles)
        }, {})

    fun getAllFilters() = filterUseCase.getAllFilters()
        .subscribe({filters ->
            Log.d("asd", filters.toString())
           _filters.postValue(filters)
        }, {
            Log.d("asd", it.message.toString())
        })

}