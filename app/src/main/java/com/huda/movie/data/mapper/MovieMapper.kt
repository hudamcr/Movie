package com.huda.movie.data.mapper

import MovieListResponse
import MovieResponse
import com.huda.movie.data.model.Movie
import com.huda.movie.presentation.utils.orZero
import com.huda.movie.data.model.MovieList

fun MovieResponse.toMovie(): Movie {
    return Movie(
        page = page.orZero(),
        results = results?.map { it.toMovieList() } ?: emptyList(),
        totalPages = totalPages.orZero(),
        totalResults = totalResults.orZero()
    )
}

fun MovieListResponse.toMovieList(): MovieList {
    return MovieList(
        id = id.orZero(),
        originalTitle = originalTitle.orEmpty(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        voteCount =voteCount.orZero()
    )
}