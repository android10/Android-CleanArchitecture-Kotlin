package com.fernandocejas.sample.features.movies

import javax.inject.Inject

class MovieDetailsPresenter
@Inject constructor(private val getMovieDetails: GetMovieDetails,
                    private val playMovie: PlayMovie) {

    internal lateinit var movieDetailsModel: MovieDetailsModel

    fun loadMovieDetails(movieId: Int) {
        movieDetailsModel.showLoading()
        getMovieDetails.execute(
                { movie ->
                    val viewModel = MovieDetailsViewModel(movie.id, movie.title, movie.poster,
                            movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
                    movieDetailsModel.renderDetails(viewModel)
                    movieDetailsModel.hideLoading() },
                GetMovieDetails.Params(movieId))
    }

    fun playMovie(url: String) = playMovie.execute({}, PlayMovie.Params(url))
}
