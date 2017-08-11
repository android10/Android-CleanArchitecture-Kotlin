package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import dagger.Lazy
import io.reactivex.Observable
import javax.inject.Inject

interface MoviesDataStore {
    fun movies(): Observable<List<Movie>>

    class Factory
    @Inject constructor(val network: Lazy<Network>, val disk: Lazy<Disk>) {
        fun network(): Network = network.get()
        fun disk(): Disk = disk.get()
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
