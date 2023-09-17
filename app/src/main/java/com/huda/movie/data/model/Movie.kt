package com.huda.movie.data.model

data class Movie(
    val page: Int,
    val results: List<MovieList>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieList(
    val id: Int,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)