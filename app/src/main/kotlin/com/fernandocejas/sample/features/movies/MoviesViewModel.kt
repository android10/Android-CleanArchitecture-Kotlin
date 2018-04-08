package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.MutableLiveData
import com.fernandocejas.sample.framework.interactor.UseCase.None
import com.fernandocejas.sample.framework.platform.BaseViewModel
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMovies.execute({ it.either(::handleFailure, ::handleMovieList) }, None())

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }
}