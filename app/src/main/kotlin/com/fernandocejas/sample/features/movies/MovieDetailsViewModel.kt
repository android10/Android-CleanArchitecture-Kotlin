package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.MutableLiveData
import com.fernandocejas.sample.features.movies.GetMovieDetails.Params
import com.fernandocejas.sample.framework.platform.BaseViewModel
import javax.inject.Inject

class MovieDetailsViewModel
@Inject constructor(private val getMovieDetails: GetMovieDetails,
                    private val playMovie: PlayMovie) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) {
        getMovieDetails.execute({ it.either(::handleFailure, ::handleMovieDetails) }, Params(movieId))
    }

    fun playMovie(url: String) = playMovie.execute({}, PlayMovie.Params(url))

    private fun handleMovieDetails(movie: MovieDetails) {
        this.movieDetails.value = MovieDetailsView(movie.id, movie.title, movie.poster,
                movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
    }
}