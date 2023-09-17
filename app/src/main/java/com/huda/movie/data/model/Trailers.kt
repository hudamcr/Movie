package com.huda.movie.data.model

data class Trailers(
    val id: Int,
    val results: List<TrailersList>
)

data class TrailersList(
    val id: String,
    val key: String
)