package com.huda.movie.data.mapper

import com.huda.movie.data.model.*
import com.huda.movie.presentation.utils.orZero

fun TrailersResponse.toTrailers(): Trailers {
    return Trailers(
        id = id.orZero(),
        results = results?.map { it.toTrailersList() } ?: emptyList()
    )
}

fun TrailersListResponse.toTrailersList(): TrailersList {
    return TrailersList(
        id = id.orEmpty(),
        key = key.orEmpty()
    )
}