package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import dagger.Lazy
import javax.inject.Inject

interface MoviesDataSource : MoviesRepository {

    class Factory
    @Inject constructor(val network: Lazy<Network>, val disk: Lazy<Disk>) {
        fun network(): Network = network.get()
        fun disk(): Disk = disk.get()
    }

    class Network
    @Inject constructor(private val restApi: RestApi) : MoviesDataSource {
        override fun movies(): List<Movie> {
            val movieList = restApi.movies().execute().body() ?: emptyList()
            return movieList.map { it.toMovie() }
        }

        override fun movieDetails(movieId: Int): MovieDetails {
            val movieDetailsEntity = restApi.movieDetails(movieId).execute().body() ?: MovieDetailsEntity.empty()
            return movieDetailsEntity.toMovieDetails()
        }
    }

    class Disk
    @Inject constructor() : MoviesDataSource {
        override fun movies(): List<Movie> = TODO()
        override fun movieDetails(movieId: Int): MovieDetails = TODO()
    }
}
