package com.fernandocejas.sample.framework.network

import com.fernandocejas.sample.features.movies.MovieEntity
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class RestApi
@Inject constructor(private val retrofit: Retrofit) : MoviesApi {

    override fun movies(): Observable<List<MovieEntity>> =
            retrofit.create(MoviesApi::class.java).movies()
}
