package com.fernandocejas.sample.features.movies

import android.content.Context
import javax.inject.Inject

class MovieDetailsPresenter
@Inject constructor(private val getMovieDetails: GetMovieDetails,
                    private val playMovie: PlayMovie) {

    internal lateinit var movieDetailsView: MovieDetailsView

    fun destroy() {
        getMovieDetails.dispose()
        playMovie.dispose()
        movieDetailsView.dispose()
    }

    fun loadMovieDetails(movieId: Int) {
        movieDetailsView.showLoading()
        getMovieDetails.execute(
                { movie ->
                    val viewModel = MovieDetailsViewModel(movie.id, movie.title, movie.poster,
                            movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
                    movieDetailsView.renderDetails(viewModel)
                    movieDetailsView.hideLoading() },
                { TODO() },
                GetMovieDetails.Params(movieId))
    }

    fun playMovie(context: Context, url: String) {
        playMovie.execute(PlayMovie.Params(context, url))
    }
}
