package com.huda.movie.data.model

data class TrailersResponse(
    val id: Int?,
    val results: List<TrailersListResponse>?
)

data class TrailersListResponse(
    val id: String?,
    val key : String?
)