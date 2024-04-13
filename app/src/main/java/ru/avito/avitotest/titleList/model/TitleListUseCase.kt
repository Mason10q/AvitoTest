package ru.avito.avitotest.titleList.model

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.titleList.model.entities.Title

interface TitleListUseCase {

    fun getTitlesPage(filters: Map<String, List<String>>) : Flow<PagingData<Title>>
    fun search(query: String, filters: Map<String, List<String>>): Flow<PagingData<Title>>

}