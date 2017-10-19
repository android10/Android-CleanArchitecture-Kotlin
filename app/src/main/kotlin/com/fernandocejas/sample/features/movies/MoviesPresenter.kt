package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.navigation.Navigator
import javax.inject.Inject

class MoviesPresenter
@Inject constructor(private val getMovies: GetMovies) {

    internal lateinit var moviesView: MoviesView

    fun destroy() {
        getMovies.dispose()
        moviesView.dispose()
    }

    fun loadMovies() {
        moviesView.showLoading()
        getMovies.execute(
                { movies -> moviesView.renderList(movies.map { MovieViewModel(it.id, it.poster) })
                            moviesView.hideLoading() },
                { TODO() })
    }

    fun onMovieClick(movieViewModel: MovieViewModel, navigationExtras: Navigator.Extras) =
            moviesView.displayDetails(movieViewModel, navigationExtras)
}
