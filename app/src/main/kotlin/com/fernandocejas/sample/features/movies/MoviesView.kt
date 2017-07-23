package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.view.LoadingView

interface MoviesView : LoadingView {
    fun renderList(movies: List<MovieViewModel>)
    fun displayDetails(movie: MovieViewModel)
}
