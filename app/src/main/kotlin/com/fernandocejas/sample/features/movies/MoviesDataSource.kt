package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import dagger.Lazy
import io.reactivex.Observable
import javax.inject.Inject

interface MoviesDataSource {
    fun movies(): Observable<List<Movie>>

    class Factory
    @Inject constructor(val network: Lazy<Network>, val disk: Lazy<Disk>) {
        fun network(): Network = network.get()
        fun disk(): Disk = disk.get()
    }

    class Network
    @Inject constructor(private val restApi: RestApi) : MoviesDataSource {
        override fun movies(): Observable<List<Movie>> =
                restApi.movies().map { movieEntities -> movieEntities.map { it.toMovie() } }
    }

    class Disk
    @Inject constructor() : MoviesDataSource {
        override fun movies(): Observable<List<Movie>> = TODO()
    }
}
