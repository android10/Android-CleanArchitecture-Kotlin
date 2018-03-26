package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fernandocejas.sample.framework.interactor.UseCase.None
import javax.inject.Inject

class MoviesViewModel(private val getMovies: GetMovies) : ViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() {
        getMovies.execute({ movieList -> movies.value = movieList.map { MovieView(it.id, it.poster) } }, None())
    }

    class Factory
    @Inject constructor(private val getMovies: GetMovies) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MoviesViewModel(getMovies) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}