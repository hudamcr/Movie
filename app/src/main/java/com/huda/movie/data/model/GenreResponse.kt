package com.huda.movie.data.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val results: List<GenreListResponse>?
)

data class GenreListResponse(
    val id: Int?,
    val name: String?
)