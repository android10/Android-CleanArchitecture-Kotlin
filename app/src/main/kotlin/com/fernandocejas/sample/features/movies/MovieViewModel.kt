package com.fernandocejas.sample.features.movies

import android.graphics.Color

class MovieViewModel(val movie: Movie) {
    fun id() = movie.id()
    fun title() = movie.title()
    fun year() = movie.year()
    fun poster() = movie.poster()
    fun color() = Color.parseColor(movie.color())
}
