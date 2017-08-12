package com.fernandocejas.sample.features.movies

data class MovieEntity(private val id: Int,
                       private val title: String,
                       private val year: Int,
                       private val poster: String,
                       private val color: String) {

    fun toMovie(): Movie {
        return Movie.create {
            this@MovieEntity.let {
                id = it.id
                title = it.title
                year = it.year
                poster = it.poster
                color = it.color
            }
        }
    }
}
