package com.huda.movie.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.huda.movie.data.model.ReviewsList
import com.huda.movie.data.repository.MovieRepository
import com.huda.movie.presentation.utils.TaskResult

// paging belum di implementasi
class ReviewsPagingSource(private val movieRepository: MovieRepository, private val movieId: Int) :
    PagingSource<Int, ReviewsList>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewsList>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewsList> {
        val pageNumber = params.key ?: 1

        return try {
            val reviews = movieRepository.getReviews(movieId, pageNumber)

            if (reviews is TaskResult.Success) {
                val data = reviews.data.results
                val prevKey = if (pageNumber == 1) null else pageNumber - 1
                val nextKey = if (pageNumber < reviews.data.totalPages) pageNumber + 1 else null

                LoadResult.Page(data = data, prevKey = prevKey, nextKey = nextKey)
            } else {
                LoadResult.Error(Exception("Failed to load reviews"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}