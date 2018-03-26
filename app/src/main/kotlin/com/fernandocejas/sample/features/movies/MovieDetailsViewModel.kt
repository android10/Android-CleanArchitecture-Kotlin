package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fernandocejas.sample.features.movies.GetMovieDetails.Params
import javax.inject.Inject

class MovieDetailsViewModel(private val getMovieDetails: GetMovieDetails,
                            private val playMovie: PlayMovie) : ViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) {
        getMovieDetails.execute(
                { movie ->
                    val movieDetailsView = MovieDetailsView(movie.id, movie.title, movie.poster,
                            movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
                    movieDetails.postValue(movieDetailsView)
                }, Params(movieId))
    }

    fun playMovie(url: String) = playMovie.execute({}, PlayMovie.Params(url))

    class Factory
    @Inject constructor(private val getMovieDetails: GetMovieDetails,
                        private val playMovie: PlayMovie) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(getMovieDetails, playMovie) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}