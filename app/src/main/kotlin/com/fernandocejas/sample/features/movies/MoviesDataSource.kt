package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import dagger.Lazy
import io.reactivex.Observable
import javax.inject.Inject

interface MoviesDataSource : MoviesRepository {

    class Factory
    @Inject constructor(val network: Lazy<Network>, val disk: Lazy<Disk>) {
        fun network(): Network = network.get()
        fun disk(): Disk = disk.get()
    }

    class Network
    @Inject constructor(private val restApi: RestApi) : MoviesDataSource {
        override fun movies(): Observable<List<Movie>> =
                restApi.movies().map { movieEntities -> movieEntities.map { it.toMovie() } }

        override fun movieDetails(movieId: Int): Observable<MovieDetails> =
                restApi.movieDetails(movieId).map { it.toMovieDetails() }
    }

    class Disk
    @Inject constructor() : MoviesDataSource {
        override fun movies(): Observable<List<Movie>> = TODO()
        override fun movieDetails(movieId: Int): Observable<MovieDetails> = TODO()
    }
}
