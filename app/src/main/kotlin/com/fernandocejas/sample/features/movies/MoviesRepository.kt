package com.fernandocejas.sample.features.movies

import io.reactivex.Single
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Single<List<Movie>>
    fun movieDetails(movieId: Int): Single<MovieDetails>

    class Source
    @Inject constructor(private val dataSourceFactory: MoviesDataSource.Factory) : MoviesRepository {
        override fun movies() = dataSourceFactory.network().movies()
        override fun movieDetails(movieId: Int) = dataSourceFactory.network().movieDetails(movieId)
    }
}
