package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.fernandocejas.sample.framework.exception.Failure
import com.fernandocejas.sample.framework.interactor.UseCase.None
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies) : ViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()
    var failure: MutableLiveData<Failure> = MutableLiveData()

    fun loadMovies() {
        getMovies.execute(
                { movieList ->
                    movies.value = movieList.map { MovieView(it.id, it.poster) }
                }, None(),
                {
                  failure.value = it
                })
    }
}