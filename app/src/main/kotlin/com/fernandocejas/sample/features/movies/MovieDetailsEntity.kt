package com.fernandocejas.sample.features.movies

data class MovieDetailsEntity(private val id: Int,
                              private val title: String,
                              private val poster: String,
                              private val summary: String,
                              private val cast: String,
                              private val director: String,
                              private val year: Int,
                              private val trailer: String) {

    fun toMovieDetails() = MovieDetails(id, title, poster, summary, cast, director, year, trailer)
}
