package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fernandocejas.sample.framework.interactor.UseCase.None
import javax.inject.Inject

class MoviesViewModel(getMovies: GetMovies) : ViewModel() {
    init {
        getMovies.execute({ movieList -> movies.value = movieList.map { MovieViewModel(it.id, it.poster) } }, None())
    }

    var movies: MutableLiveData<List<MovieViewModel>> = MutableLiveData()

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