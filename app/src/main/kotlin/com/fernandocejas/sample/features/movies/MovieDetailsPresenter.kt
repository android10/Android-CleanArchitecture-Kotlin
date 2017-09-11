package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCaseObserver
import javax.inject.Inject

class MovieDetailsPresenter
@Inject constructor(private val getMovieDetails: GetMovieDetails) {

    internal lateinit var movieDetailsView: MovieDetailsView

    fun destroy() {
        getMovieDetails.dispose()
        movieDetailsView.dispose()
    }

    fun loadMovieDetails(movieId: Int) {
        movieDetailsView.showLoading()
        getMovieDetails.execute(MovieDetailsObserver(), GetMovieDetails.Params(movieId))
    }

    internal inner class MovieDetailsObserver : UseCaseObserver<MovieDetails>() {
        override fun onComplete() = movieDetailsView.hideLoading()
        override fun onNext(value: MovieDetails) = movieDetailsView.renderDetails(MovieDetailsViewModel(value.id))
        override fun onError(e: Throwable) = TODO()
    }
}
