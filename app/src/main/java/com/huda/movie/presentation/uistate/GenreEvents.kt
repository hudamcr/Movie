package com.huda.movie.presentation.uistate

import com.huda.movie.data.model.Genre

sealed class GenreEvents {
    object Empty : GenreEvents()
    data class Success(val results: Genre) : GenreEvents()
}