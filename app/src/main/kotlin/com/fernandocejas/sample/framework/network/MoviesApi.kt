package com.fernandocejas.sample.framework.network

import com.fernandocejas.sample.features.movies.MovieEntity
import io.reactivex.Observable
import retrofit2.http.GET

internal interface MoviesApi {
    @GET(Endpoints.MOVIES) fun movies(): Observable<List<MovieEntity>>
}
