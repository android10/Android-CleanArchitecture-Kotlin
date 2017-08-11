package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import io.reactivex.Observable
import javax.inject.Inject

interface MoviesDataStore {
    fun movies(): Observable<List<Movie>>

    class Factory //TODO: constructor collaborators should be lazy
    @Inject constructor(val network: Network, val disk: Disk) {
        fun network() = network
        fun disk() = disk
    }

    class Network
    @Inject constructor(private val restApi: RestApi) : MoviesDataStore {
        override fun movies(): Observable<List<Movie>> = restApi.movies().map { convert(it) }

        //TODO: a bit ugly: fix this
        private fun convert(movieEntities: List<MovieEntity>): List<Movie> {
            val list: MutableList<Movie> = mutableListOf()

            movieEntities.forEach {
                list.add(it.convert())
            }

            return list
        }
    }

    class Disk
    @Inject constructor() : MoviesDataStore {
        override fun movies(): Observable<List<Movie>> {
            TODO()
        }
    }
}
