package com.huda.movie.presentation.uistate

import com.huda.movie.data.model.Movie

sealed class MovieEvents {
    object Empty : MovieEvents()
    data class Success(val results: Movie) : MovieEvents()
}