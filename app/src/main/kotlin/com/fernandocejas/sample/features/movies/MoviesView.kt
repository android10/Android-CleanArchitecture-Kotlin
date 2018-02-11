package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.view.LoadingView
import com.fernandocejas.sample.navigation.Navigator

interface MoviesView : LoadingView {
    fun renderList(movies: List<MovieViewModel>)
    fun displayDetails(movie: MovieViewModel, navigationExtras: Navigator.Extras)
}
