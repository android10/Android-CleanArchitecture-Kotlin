package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCaseObserver
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
        getMovies.execute(MoviesObserver())
    }

    fun onMovieClick(movieViewModel: MovieViewModel, navigationExtras: Navigator.Extras) =
            moviesView.displayDetails(movieViewModel, navigationExtras)

    internal inner class MoviesObserver : UseCaseObserver<List<Movie>>() {
        override fun onComplete() = moviesView.hideLoading()
        override fun onNext(value: List<Movie>) = moviesView.renderList(value.map { MovieViewModel(it.id, it.poster) })
        override fun onError(e: Throwable) = TODO()
    }
}
