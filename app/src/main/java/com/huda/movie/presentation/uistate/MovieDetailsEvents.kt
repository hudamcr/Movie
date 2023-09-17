package com.huda.movie.presentation.uistate

import com.huda.movie.data.model.MovieDetails

sealed class MovieDetailsEvents {
    object Empty : MovieDetailsEvents()
    data class Success(val results: MovieDetails) : MovieDetailsEvents()
}