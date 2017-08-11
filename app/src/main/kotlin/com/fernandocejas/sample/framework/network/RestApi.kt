package com.fernandocejas.sample.framework.network

import com.fernandocejas.sample.features.movies.MovieEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestApi
@Inject constructor(retrofit: Retrofit) : MoviesApi {
    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    //TODO: fix these schedulers, should not be here
    override fun movies(): Observable<List<MovieEntity>> =
            moviesApi.movies()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
}
