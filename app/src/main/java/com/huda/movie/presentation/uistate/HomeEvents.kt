package com.huda.movie.presentation.uistate

import com.huda.movie.data.model.Movie

sealed class HomeEvents {
    object Empty : HomeEvents()
    data class Success(val results: Movie) : HomeEvents()
}