package ru.avito.avitotest.title.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ru.avito.avitotest.title.model.TitleUseCase
import ru.avito.avitotest.title.model.entities.Poster
import ru.avito.avitotest.title.model.entities.Title
import javax.inject.Inject

class TitleViewModel @Inject constructor(private val titleUseCase: TitleUseCase): ViewModel() {

    var titleId = 0

    private val _title = MutableLiveData<Title>()
    val title: LiveData<Title> = _title

    private val _posters = MutableLiveData<List<Poster>>()
    val posters: LiveData<List<Poster>> = _posters

    fun getTitle() = titleUseCase.getTitleById(titleId)
        .subscribe({
           _title.postValue(it)
        }, {})


    fun getPosters() = titleUseCase.getPostersBuTitleId(titleId)
        .subscribe({
           _posters.postValue(it)
        }, {})

    fun getReviewDataFlow() = titleUseCase.getReviewFLowByTitleId(titleId)
        .cachedIn(viewModelScope)

}