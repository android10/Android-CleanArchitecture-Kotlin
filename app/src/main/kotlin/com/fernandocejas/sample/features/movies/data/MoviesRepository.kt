/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.movies.data

import com.fernandocejas.sample.core.failure.Failure
import com.fernandocejas.sample.core.failure.Failure.NetworkConnection
import com.fernandocejas.sample.core.failure.Failure.ServerError
import com.fernandocejas.sample.core.functional.Either
import com.fernandocejas.sample.core.functional.Either.Left
import com.fernandocejas.sample.core.functional.Either.Right
import com.fernandocejas.sample.core.network.NetworkHandler
import com.fernandocejas.sample.features.movies.interactor.Movie
import com.fernandocejas.sample.features.movies.interactor.MovieDetails
import retrofit2.Call

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: MoviesService
    ) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.movies(),
                    { it.map { movieEntity -> movieEntity.toMovie() } },
                    emptyList()
                )
                false -> Left(NetworkConnection)
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.movieDetails(movieId),
                    { it.toMovieDetails() },
                    MovieDetailsEntity.empty
                )
                false -> Left(NetworkConnection)
            }
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError)
                }
            } catch (exception: Throwable) {
                Left(ServerError)
            }
        }
    }
}
