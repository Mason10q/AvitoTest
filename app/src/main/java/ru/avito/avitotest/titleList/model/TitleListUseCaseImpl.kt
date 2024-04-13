package ru.avito.avitotest.titleList.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.paging.SearchPagingSource
import ru.avito.avitotest.data.paging.TitlePagingSource
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.data.network.retrofit.ProxyRetrofitQueryMap
import javax.inject.Inject

class TitleListUseCaseImpl @Inject constructor(
    private val repository: TitleListRepository,
    private val titleMapper: Mapper<Title, TitleDto>
) : TitleListUseCase {

    private val pagerConfig = PagingConfig(
        pageSize = 10,
        initialLoadSize = 20,
        prefetchDistance = 3,
        enablePlaceholders = false
    )

    override fun search(query: String, filters: Map<String, List<String>>): Flow<PagingData<Title>> =
        Pager(pagerConfig, initialKey = 1,
            pagingSourceFactory = { SearchPagingSource(repository, query, titleMapper) }
        ).flow

    override fun getTitlesPage(filters: Map<String, List<String>>) : Flow<PagingData<Title>> =
        Pager(pagerConfig, initialKey = 1,
            pagingSourceFactory = { TitlePagingSource(repository, ProxyRetrofitQueryMap(filters), titleMapper) }
        ).flow

}