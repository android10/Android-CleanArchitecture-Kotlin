package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.exception.Failure
import com.fernandocejas.sample.framework.exception.Failure.NetworkConnection
import com.fernandocejas.sample.framework.exception.Failure.ServerError
import com.fernandocejas.sample.framework.functional.Either
import com.fernandocejas.sample.framework.functional.Either.Left
import com.fernandocejas.sample.framework.functional.Either.Right
import com.fernandocejas.sample.framework.platform.NetworkHandler
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: MoviesService) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> requestMovies()
                false -> Left(NetworkConnection())
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true -> requestMovieDetails(movieId)
                false -> Left(NetworkConnection())
            }
        }

        private fun requestMovies(): Either<Failure, List<Movie>> {
            return try {
                val response = service.movies().execute()
                if (response.isSuccessful) {
                    Right((response.body() ?: emptyList()).map { it.toMovie() })
                } else {
                    Left(ServerError())
                }
            } catch (exception: Throwable) {
                Left(ServerError())
            }
        }

        private fun requestMovieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return try {
                val response = service.movieDetails(movieId).execute()
                if (response.isSuccessful) {
                    Right((response.body() ?: MovieDetailsEntity.empty()).toMovieDetails())
                } else {
                    Left(ServerError())
                }
            } catch (exception: Throwable) {
                Left(ServerError())
            }
        }
    }
}
