package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCaseObserver
import javax.inject.Inject

class MoviesPresenter @Inject constructor(val getMovies: GetMovies) {

    internal var moviesView: MoviesView? = null

    fun destroy() {
        //TODO: better approach for handling lifecycle
    }

    fun loadMovies() {
        moviesView?.let {
            it.showLoading()
            //TODO: avoid passing null: refactor
            getMovies.execute(MoviesObserver(), null)
        }
    }

    private inner class MoviesObserver : UseCaseObserver<List<Movie>>() {
        override fun onComplete() {
            moviesView?.hideLoading()
        }

        override fun onNext(value: List<Movie>) {
            moviesView?.renderList(value.map(::MovieViewModel))
        }

        override fun onError(e: Throwable?) {
            //TODO: handle errors
        }
    }
}
