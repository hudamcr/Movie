package com.huda.movie.data.repository

import com.huda.movie.data.model.*
import com.huda.movie.presentation.utils.TaskResult

interface MovieRepository {
    suspend fun getMovie(genreId: Int, page: Int): TaskResult<Movie>
    suspend fun getGenre(): TaskResult<Genre>
    suspend fun getMovieDetails(moveId: Int): TaskResult<MovieDetails>
    suspend fun getTrailers(movieId: Int): TaskResult<Trailers>
    suspend fun getReviews(movieId: Int, page : Int): TaskResult<Reviews>
}