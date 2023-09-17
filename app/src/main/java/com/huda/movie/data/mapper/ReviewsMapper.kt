package com.huda.movie.data.mapper

import com.huda.movie.data.model.Reviews
import com.huda.movie.data.model.ReviewsList
import com.huda.movie.data.model.ReviewsListResponse
import com.huda.movie.data.model.ReviewsResponse
import com.huda.movie.presentation.utils.orZero

fun ReviewsResponse.toReviews(): Reviews {
    return Reviews(
        id = id.orZero(),
        totalPages = totalPages.orZero(),
        results = results?.map { it.toReviewsList() } ?: emptyList()
    )
}

fun ReviewsListResponse.toReviewsList(): ReviewsList {
    return ReviewsList(
        id = id.orEmpty(),
        author = author.orEmpty(),
        content = content.orEmpty()
    )
}