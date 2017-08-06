package com.fernandocejas.sample.features.movies

import io.reactivex.Observable
import javax.inject.Inject

interface MoviesDataStore {
    fun movies(): Observable<List<Movie>>

    class Factory @Inject constructor() {
        fun create() : MoviesDataStore = Network()
    }

    private class Network : MoviesDataStore {
        override fun movies(): Observable<List<Movie>> {
            val movieOne = Movie.create { title = "Iron Man 3" }
            val movieTwo = Movie.create { title = "Superman" }

            return Observable.just(listOf(movieOne, movieTwo))
        }
    }

    private class Disk : MoviesDataStore {
        override fun movies(): Observable<List<Movie>> {
            TODO()
        }
    }
}
