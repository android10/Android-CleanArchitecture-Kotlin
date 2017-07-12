package com.fernandocejas.sample.features.movies

interface MoviesView {
    fun renderList(movies: List<MovieViewModel>)
    fun displayDetails(movie: MovieViewModel)
}
