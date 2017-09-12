package com.fernandocejas.sample.framework.network

import com.fernandocejas.sample.features.movies.MovieDetailsEntity
import com.fernandocejas.sample.features.movies.MovieEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {
    @GET(Endpoints.MOVIES) fun movies(): Observable<List<MovieEntity>>
    @GET(Endpoints.MOVIE_DETAILS) fun movieDetails(@Path(Endpoints.PARAM_MOVIE_ID) movieId: Int): Observable<MovieDetailsEntity>
}
