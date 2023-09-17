package com.huda.movie.presentation.uistate

import com.huda.movie.data.model.Genre
import com.huda.movie.data.model.Trailers

sealed class TrailersEvents {
    object Empty : TrailersEvents()
    data class Success(val results: Trailers) : TrailersEvents()
}