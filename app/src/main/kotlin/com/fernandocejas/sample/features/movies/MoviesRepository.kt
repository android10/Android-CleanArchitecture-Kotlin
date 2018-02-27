package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.network.RestApi
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): List<Movie>
    fun movieDetails(movieId: Int): MovieDetails

    class NetworkDataSource
    @Inject constructor(private val restApi: RestApi) : MoviesRepository {

        override fun movies(): List<Movie> {
            val movieList = restApi.movies().execute().body() ?: emptyList()
            return movieList.map { it.toMovie() }
        }

        override fun movieDetails(movieId: Int): MovieDetails {
            val movieDetailsEntity = restApi.movieDetails(movieId).execute().body()
                    ?: MovieDetailsEntity.empty()
            return movieDetailsEntity.toMovieDetails()
        }
    }
}
