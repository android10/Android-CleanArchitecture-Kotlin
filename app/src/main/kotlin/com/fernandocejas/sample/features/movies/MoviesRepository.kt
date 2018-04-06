package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.exception.Failure
import com.fernandocejas.sample.framework.functional.Either
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network
    @Inject constructor(private val service: MoviesService) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            val movieList = service.movies().execute().body() ?: emptyList()
            return movieList.map { it.toMovie() }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            val movieDetailsEntity = service.movieDetails(movieId).execute().body() ?: MovieDetailsEntity.empty()
            return movieDetailsEntity.toMovieDetails()
        }
    }
}
