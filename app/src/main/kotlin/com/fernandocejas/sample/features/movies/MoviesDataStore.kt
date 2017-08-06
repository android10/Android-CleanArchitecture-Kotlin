package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import io.reactivex.Observable
import javax.inject.Inject

interface MoviesDataStore {
    fun movies(): Observable<List<Movie>>

    class Factory //TODO: constructor collaborators should be lazy
    @Inject constructor(val network: Network, val disk: Disk) {
        fun network() = network
    }

    class Network
    @Inject constructor(private val restApi: RestApi) : MoviesDataStore {
        override fun movies(): Observable<List<Movie>> {
            return restApi.movies().map { movieEntities -> convert(movieEntities) }
        }

        private fun convert (moviesEntities: List<MovieEntity>) : List<Movie> {
            TODO()
        }
    }

    class Disk
    @Inject constructor() : MoviesDataStore {
        override fun movies(): Observable<List<Movie>> {
            TODO()
        }
    }
}
