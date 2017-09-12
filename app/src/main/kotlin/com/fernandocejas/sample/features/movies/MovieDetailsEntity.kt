package com.fernandocejas.sample.features.movies

data class MovieDetailsEntity(private val id: Int,
                              private val title: String,
                              private val poster: String,
                              private val summary: String,
                              private val cast: String,
                              private val director: String,
                              private val year: Int,
                              private val trailer: String) {

    fun toMovieDetails(): MovieDetails {
        return MovieDetails.create {
            this@MovieDetailsEntity.let {
                id = it.id
                title = it.title
                poster = it.poster
                summary = it.summary
                cast = it.cast
                director = it.director
                year = it.year
                trailer = it.trailer
            }
        }
    }
}
