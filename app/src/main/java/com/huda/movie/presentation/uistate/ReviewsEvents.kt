package com.huda.movie.presentation.uistate

import com.huda.movie.data.model.Reviews

sealed class ReviewsEvents {
    object Empty : ReviewsEvents()
    data class Success(val results: Reviews) : ReviewsEvents()
}