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
import com.fernandocejas.sample.core.functional.toLeft
import com.fernandocejas.sample.core.network.ApiResponse
import com.fernandocejas.sample.core.network.NetworkHandler
import com.fernandocejas.sample.core.network.toEither
import com.fernandocejas.sample.features.movies.interactor.Movie
import com.fernandocejas.sample.features.movies.interactor.MovieDetails

interface MoviesRepository {
    suspend fun movies(): Either<Failure, List<Movie>>
    suspend fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: MoviesService
    ) : MoviesRepository {

        override suspend fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> {
                    service.movies()
                        .toEither(
                            successTransform = { it.map { movieEntity -> movieEntity.toMovie() } },
                            errorTransform = {
                                when (it) {
                                    is ApiResponse.Error.HttpError<*> -> ServerError
                                    is ApiResponse.Error.NetworkError -> NetworkConnection
                                    is ApiResponse.Error.SerializationError -> ServerError
                                }
                            },
                        )
                }

                false -> NetworkConnection.toLeft()
            }
        }

        override suspend fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> {
                    return service.movieDetails(movieId)
                        .toEither(
                            successTransform = { it.toMovieDetails() },
                            errorTransform = {
                                when (it) {
                                    is ApiResponse.Error.HttpError<*> -> ServerError
                                    is ApiResponse.Error.NetworkError -> NetworkConnection
                                    is ApiResponse.Error.SerializationError -> ServerError
                                }
                            },
                        )
                }

                false -> NetworkConnection.toLeft()
            }
        }
    }
}
