package com.huda.movie.data.repository

import com.huda.movie.BuildConfig
import com.huda.movie.data.TmdbApiService
import com.huda.movie.data.mapper.*
import com.huda.movie.data.model.*
import com.huda.movie.presentation.utils.ApiResponseCodeMapper
import com.huda.movie.presentation.utils.TaskResult
import com.huda.movie.presentation.utils.tryCatchTaskResult
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val service: TmdbApiService
) : MovieRepository {

    val apiKey = BuildConfig.API_KEY

    override suspend fun getMovie(genreId: Int, page: Int): TaskResult<Movie> {
        return tryCatchTaskResult(
            tryAction = {
                val response = service.getMoviesByGenre(apiKey, genreId, page)
                val error = ApiResponseCodeMapper(response.code(), null, "")

                if (!response.isSuccessful) {
                    return@tryCatchTaskResult TaskResult.Failure(error)
                }

                val movie = response.body()?.toMovie()
                    ?: return@tryCatchTaskResult TaskResult.Failure(error)
                TaskResult.Success(movie)
            },
            catchAction = { error ->
                TaskResult.Failure(error)
            }
        )
    }

    override suspend fun getGenre(): TaskResult<Genre> {
        return tryCatchTaskResult(
            tryAction = {
                val response = service.getGenres(apiKey)
                val error = ApiResponseCodeMapper(response.code(), null, "")

                if (!response.isSuccessful) {
                    return@tryCatchTaskResult TaskResult.Failure(error)
                }

                val genre = response.body()?.toGenre()
                    ?: return@tryCatchTaskResult TaskResult.Failure(error)
                TaskResult.Success(genre)
            },
            catchAction = { error ->
                TaskResult.Failure(error)
            }
        )
    }

    override suspend fun getMovieDetails(movieId: Int): TaskResult<MovieDetails> {
        return tryCatchTaskResult(
            tryAction = {
                val response = service.getMovieDetails(movieId, apiKey)
                val error = ApiResponseCodeMapper(response.code(), null, "")

                if (!response.isSuccessful) {
                    return@tryCatchTaskResult TaskResult.Failure(error)
                }

                val movie = response.body()?.toMovieDetails()
                    ?: return@tryCatchTaskResult TaskResult.Failure(error)

                TaskResult.Success(movie)
            },
            catchAction = { error ->
                TaskResult.Failure(error)
            }
        )
    }

    override suspend fun getTrailers(movieId: Int): TaskResult<Trailers> {
        return tryCatchTaskResult(
            tryAction = {
                val response = service.getTrailers(movieId, apiKey)
                val error = ApiResponseCodeMapper(response.code(), null, "")

                if (!response.isSuccessful) {
                    return@tryCatchTaskResult TaskResult.Failure(error)
                }

                val trailers = response.body()?.toTrailers()
                    ?: return@tryCatchTaskResult TaskResult.Failure(error)
                TaskResult.Success(trailers)
            },
            catchAction = { error ->
                TaskResult.Failure(error)
            }
        )
    }

    override suspend fun getReviews(movieId: Int, page: Int): TaskResult<Reviews> {
        return tryCatchTaskResult(
            tryAction = {
                val response = service.getReviews(movieId, apiKey, page)
                val error = ApiResponseCodeMapper(response.code(), null, "")

                if (!response.isSuccessful) {
                    return@tryCatchTaskResult TaskResult.Failure(error)
                }

                val trailers = response.body()?.toReviews()
                    ?: return@tryCatchTaskResult TaskResult.Failure(error)
                TaskResult.Success(trailers)
            },
            catchAction = { error ->
                TaskResult.Failure(error)
            }
        )
    }

}