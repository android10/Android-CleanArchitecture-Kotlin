package com.fernandocejas.sample.features.movies

import javax.inject.Inject

interface MoviesRepository {
    fun movies(): List<Movie>
    fun movieDetails(movieId: Int): MovieDetails

    class Source
    @Inject constructor(private val dataSourceFactory: MoviesDataSource.Factory) : MoviesRepository {
        override fun movies() = dataSourceFactory.network().movies()
        override fun movieDetails(movieId: Int) = dataSourceFactory.network().movieDetails(movieId)
    }
}
