package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCaseObserver
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

    private inner class MoviesObserver : UseCaseObserver<List<Movie>>() {
        override fun onComplete() = moviesView.hideLoading()
        override fun onNext(value: List<Movie>) = moviesView.renderList(value.map(::MovieViewModel))
        override fun onError(e: Throwable) = TODO()
    }
}
