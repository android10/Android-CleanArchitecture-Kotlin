package com.fernandocejas.sample.framework.network

import com.fernandocejas.sample.features.movies.MovieEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestApi
@Inject constructor(retrofit: Retrofit) : MoviesApi {
    private val moviesApi = retrofit.create(MoviesApi::class.java)

    override fun movies(): Observable<List<MovieEntity>> = moviesApi.movies().subscribeOn(Schedulers.newThread())
}
