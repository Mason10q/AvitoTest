package ru.avito.avitotest.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.data.network.dtos.DocsDto
import ru.avito.avitotest.data.network.dtos.ReviewDto
import ru.avito.avitotest.title.model.entities.Review

class ReviewPagingSource(
    private val repository: TitleListRepository,
    private val movieId: Int,
    private val reviewMapper: Mapper<Review, ReviewDto>
): RxPagingSource<Int, Review>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Review>> =
        repository.getReviewsByTitleId(pageNum = params.key ?: 1, id = movieId)
            .map { toLoadResult(it, params.key ?: 1) }
            .onErrorReturn { LoadResult.Error(it) }



    private fun toLoadResult(data: DocsDto<ReviewDto>, position: Int): LoadResult<Int, Review> {
        return LoadResult.Page(
            data = data.data?.map { reviewMapper.map(it) } ?: emptyList(),
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.total) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}