package com.fernandocejas.sample.features.movies

import javax.inject.Inject

interface MoviesRepository {
    fun movies(): List<Movie>
    fun movieDetails(movieId: Int): MovieDetails

    class Network
    @Inject constructor(private val service: MoviesService) : MoviesRepository {

        override fun movies(): List<Movie> {
            val movieList = service.movies().execute().body() ?: emptyList()
            return movieList.map { it.toMovie() }
        }

        override fun movieDetails(movieId: Int): MovieDetails {
            val movieDetailsEntity = service.movieDetails(movieId).execute().body() ?: MovieDetailsEntity.empty()
            return movieDetailsEntity.toMovieDetails()
        }
    }
}
