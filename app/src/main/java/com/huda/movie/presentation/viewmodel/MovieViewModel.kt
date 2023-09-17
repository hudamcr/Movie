package com.huda.movie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.huda.movie.data.model.ReviewsList
import com.huda.movie.data.repository.MovieRepository
import com.huda.movie.presentation.paging.ReviewsPagingSource
import com.huda.movie.presentation.uistate.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private var _home = MutableLiveData<HomeEvents>()
    val home: LiveData<HomeEvents> get() = _home

    private var _movies = MutableLiveData<MovieEvents>()
    val movies: LiveData<MovieEvents> get() = _movies

    private var _genres = MutableLiveData<GenreEvents>()
    val genres: LiveData<GenreEvents> get() = _genres

    private var _movieDetails = MutableLiveData<MovieDetailsEvents>()
    val movieDetails: LiveData<MovieDetailsEvents> get() = _movieDetails

    private var _trailers = MutableLiveData<TrailersEvents>()
    val trailers: LiveData<TrailersEvents> get() = _trailers

    private var _reviews = MutableLiveData<ReviewsEvents>()
    val reviews: LiveData<ReviewsEvents> get() = _reviews


    fun getHome(genreId: Int, page: Int) {
        viewModelScope.launch {
            movieRepository.getMovie(genreId, page).fold(
                success = {
                    _home.postValue(HomeEvents.Success(it))
                },
                failure = {
                    _home.postValue(HomeEvents.Empty)
                }
            )
        }
    }

    fun getMovies(genreId: Int, page: Int) {
        viewModelScope.launch {
            movieRepository.getMovie(genreId, page).fold(
                success = {
                    _movies.postValue(MovieEvents.Success(it))
                },
                failure = {
                    _movies.postValue(MovieEvents.Empty)
                }
            )
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            movieRepository.getGenre().fold(
                success = {
                    _genres.postValue(GenreEvents.Success(it))
                },
                failure = {
                    _genres.postValue(GenreEvents.Empty)
                }
            )
        }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId).fold(
                success = {
                    _movieDetails.postValue(MovieDetailsEvents.Success(it))
                },
                failure = {
                    _movieDetails.postValue(MovieDetailsEvents.Empty)
                }
            )
        }
    }

    fun getMovieTrailers(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getTrailers(movieId).fold(
                success = {
                    _trailers.postValue(TrailersEvents.Success(it))
                },
                failure = {
                    _trailers.postValue(TrailersEvents.Empty)
                }
            )
        }
    }

    fun getReviews(movieId: Int, page: Int) {
        viewModelScope.launch {
            movieRepository.getReviews(movieId, page).fold(
                success = {
                    _reviews.postValue(ReviewsEvents.Success(it))
                },
                failure = {
                    _reviews.postValue(ReviewsEvents.Empty)
                }
            )
        }
    }
}