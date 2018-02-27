package com.fernandocejas.sample.features.movies

interface MoviesRepository {
    fun movies(): List<Movie>
    fun movieDetails(movieId: Int): MovieDetails
}
