package ru.avito.avitotest.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.data.network.dtos.DocsDto
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.titleList.model.entities.Title

class SearchPagingSource(
    private val repository: TitleListRepository,
    private val query: String,
    private val titleMapper: Mapper<Title, TitleDto>
): RxPagingSource<Int, Title>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Title>> =
        repository.search(params.key ?: 1, query)
            .map { toLoadResult(it, params.key ?: 1) }
            .onErrorReturn { LoadResult.Error(it) }



    private fun toLoadResult(data: DocsDto<TitleDto>, position: Int): LoadResult<Int, Title> {
        return LoadResult.Page(
            data = data.data?.map { titleMapper.map(it) } ?: emptyList(),
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.total) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Title>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}