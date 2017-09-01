package com.fernandocejas.sample.features.movies

data class MovieEntity(private val id: Int, private val poster: String) {

    fun toMovie(): Movie {
        return Movie.create {
            this@MovieEntity.let {
                id = it.id
                poster = it.poster
            }
        }
    }
}
