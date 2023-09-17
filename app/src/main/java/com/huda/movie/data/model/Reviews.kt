package com.huda.movie.data.model

data class Reviews(
    val id: Int,
    val totalPages: Int,
    val results: List<ReviewsList>
)

data class ReviewsList(
    val id: String,
    val author: String,
    val content: String
)