package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCase.None
import com.fernandocejas.sample.navigation.Navigator
import javax.inject.Inject

class MoviesPresenter
@Inject constructor(private val getMovies: GetMovies) {

    internal lateinit var moviesView: MoviesView

    fun loadMovies() {
        moviesView.showLoading()
        getMovies.execute(
                { movies -> moviesView.renderList(movies.map { MovieViewModel(it.id, it.poster) })
                            moviesView.hideLoading() }, None())
    }

    fun onMovieClick(movieViewModel: MovieViewModel, navigationExtras: Navigator.Extras) =
            moviesView.displayDetails(movieViewModel, navigationExtras)
}
