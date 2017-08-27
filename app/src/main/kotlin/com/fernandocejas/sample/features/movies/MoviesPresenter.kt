package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.features.base.BasePresenter
import com.fernandocejas.sample.framework.interactor.UsecaseDisposableObserver
import javax.inject.Inject

class MoviesPresenter
@Inject constructor(private val getMovies: GetMovies) : BasePresenter<MoviesView>() {

    override lateinit var view: MoviesView

    fun destroy() {
        getMovies.dispose()
        finalize()
    }

    fun loadMovies() {
        view.showLoading()
        getMovies.execute(UsecaseDisposableObserver(
                { onComplete() },
                { onGetMoviesNext(it) },
                { onError(it) }))
    }

    fun onGetMoviesNext(value: List<Movie>) = view.renderList(value.map(::MovieViewModel))
}
