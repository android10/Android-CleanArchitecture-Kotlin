package com.fernandocejas.sample.features.movies

import io.reactivex.Observable
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Observable<List<Movie>>

    class Source
    @Inject constructor(private val dataStoreFactory: MoviesDataStore.Factory) : MoviesRepository {
        override fun movies(): Observable<List<Movie>> = dataStoreFactory.network().movies()
    }
}
