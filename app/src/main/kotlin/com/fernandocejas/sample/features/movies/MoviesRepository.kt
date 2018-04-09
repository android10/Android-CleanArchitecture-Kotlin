package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.exception.Failure
import com.fernandocejas.sample.framework.exception.Failure.NetworkConnection
import com.fernandocejas.sample.framework.exception.Failure.ServerError
import com.fernandocejas.sample.framework.functional.Either
import com.fernandocejas.sample.framework.functional.Either.Left
import com.fernandocejas.sample.framework.functional.Either.Right
import com.fernandocejas.sample.framework.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: MoviesService) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> request(service.movies(), { it.map { it.toMovie() } }, emptyList())
                false -> Left(NetworkConnection())
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true -> request(service.movieDetails(movieId), { it.toMovieDetails() }, MovieDetailsEntity.empty())
                false -> Left(NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError())
                }
            } catch (exception: Throwable) {
                Left(ServerError())
            }
        }
    }
}
