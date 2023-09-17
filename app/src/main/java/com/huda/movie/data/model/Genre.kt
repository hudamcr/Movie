package com.huda.movie.data.model

data class Genre(
    val results: List<GenreList>
)

data class GenreList(
    val id: Int,
    val name: String
)