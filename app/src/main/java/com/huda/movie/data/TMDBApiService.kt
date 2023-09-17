package com.huda.movie.data

import MovieResponse
import com.huda.movie.data.model.GenreResponse
import com.huda.movie.data.model.MovieDetailsResponse
import com.huda.movie.data.model.ReviewsResponse
import com.huda.movie.data.model.TrailersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getTrailers(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<TrailersResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): Response<ReviewsResponse>
}