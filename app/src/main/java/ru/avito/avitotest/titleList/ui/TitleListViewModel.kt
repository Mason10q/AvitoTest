package ru.avito.avitotest.titleList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.avito.avitotest.titleList.model.TitleListUseCase
import ru.avito.avitotest.titleList.model.TitleListUseCaseImpl
import ru.avito.avitotest.titleList.model.entities.Title
import javax.inject.Inject

class TitleListViewModel @Inject constructor(private val titleUseCase: TitleListUseCase): ViewModel() {

    private val _titles = MutableLiveData<List<Title>>()
    val titles: LiveData<List<Title>> = _titles

    private val currentPage = 1

    fun getTitlesPage() = titleUseCase.getTitlesPage(currentPage)
        .subscribe({ titles ->
            _titles.postValue(titles)
        }, {})

}