package com.huda.movie.data.mapper

import com.huda.movie.data.model.Genre
import com.huda.movie.data.model.GenreList
import com.huda.movie.data.model.GenreListResponse
import com.huda.movie.data.model.GenreResponse
import com.huda.movie.presentation.utils.orZero

fun GenreResponse.toGenre(): Genre {
    return Genre(
        results = results?.map { it.toGenreList() } ?: emptyList()
    )
}

fun GenreListResponse.toGenreList(): GenreList {
    return GenreList(
        id = id.orZero(),
        name = name.orEmpty()
    )
}