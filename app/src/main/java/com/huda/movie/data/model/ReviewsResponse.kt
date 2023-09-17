package com.huda.movie.data.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    val id: Int?,
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    val results: List<ReviewsListResponse>?
)

data class ReviewsListResponse(
    val id: String?,
    val author: String?,
    val content: String?
)