package com.huda.movie.di

import com.huda.movie.data.repository.MovieRepository
import com.huda.movie.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MovieModule {
    @Binds
    abstract fun provideMovieRepo(repo: MovieRepositoryImpl): MovieRepository
}