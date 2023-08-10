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

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.core.extension.empty
import com.fernandocejas.sample.core.failure.Failure
import com.fernandocejas.sample.core.failure.Failure.NetworkConnection
import com.fernandocejas.sample.core.failure.Failure.ServerError
import com.fernandocejas.sample.core.functional.Either
import com.fernandocejas.sample.core.functional.Either.Right
import com.fernandocejas.sample.core.network.NetworkHandler
import com.fernandocejas.sample.features.movies.data.MovieDetailsEntity
import com.fernandocejas.sample.features.movies.data.MovieEntity
import com.fernandocejas.sample.features.movies.data.MoviesRepository
import com.fernandocejas.sample.features.movies.data.MoviesRepository.Network
import com.fernandocejas.sample.features.movies.data.MoviesService
import com.fernandocejas.sample.features.movies.interactor.Movie
import com.fernandocejas.sample.features.movies.interactor.MovieDetails
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class MoviesRepositoryTest : UnitTest() {

    private lateinit var networkRepository: MoviesRepository.Network

    private val networkHandler: NetworkHandler = mockk()
    private val service: MoviesService = mockk()

    private val moviesCall: Call<List<MovieEntity>> = mockk()
    private val moviesResponse: Response<List<MovieEntity>> = mockk()
    private val movieDetailsCall: Call<MovieDetailsEntity> = mockk()
    private val movieDetailsResponse: Response<MovieDetailsEntity> = mockk()

    @Before
    fun setUp() {
        networkRepository = Network(networkHandler, service)
    }

    @Test
    fun `should return empty list by default`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesResponse.body() } returns null
        every { moviesResponse.isSuccessful } returns true
        every { moviesCall.execute() } returns moviesResponse
        every { service.movies() } returns moviesCall

        val movies = networkRepository.movies()

        movies shouldBeEqual Right(emptyList())
        verify(exactly = 1) { service.movies() }
    }

    @Test
    fun `should get movie list from service`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesResponse.body() } returns listOf(MovieEntity(1, "poster"))
        every { moviesResponse.isSuccessful } returns true
        every { moviesCall.execute() } returns moviesResponse
        every { service.movies() } returns moviesCall

        val movies = networkRepository.movies()

        movies shouldBeEqual Right(listOf(Movie(1, "poster")))
        verify(exactly = 1) { service.movies() }
    }

    @Test
    fun `movies service should return network failure when no connection`() {
        every { networkHandler.isNetworkAvailable() } returns false

        val movies = networkRepository.movies()

        movies should beInstanceOf<Either<Failure, List<Movie>>>()
        movies.isLeft shouldBeEqual true
        movies.fold({ failure -> failure should beInstanceOf<NetworkConnection>() }, {})
        verify { service wasNot Called }
    }

    @Test
    fun `movies service should return server error if no successful response`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesResponse.isSuccessful } returns false
        every { moviesCall.execute() } returns moviesResponse
        every { service.movies() } returns moviesCall

        val movies = networkRepository.movies()

        movies.isLeft shouldBeEqual true
        movies.fold({ failure -> failure should beInstanceOf<ServerError>() }, {})
    }

    @Test
    fun `movies request should catch exceptions`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { moviesCall.execute() } returns moviesResponse
        every { service.movies() } returns moviesCall

        val movies = networkRepository.movies()

        movies.isLeft shouldBe true
        movies.fold({ failure -> failure should beInstanceOf<ServerError>() }, {})
    }

    @Test
    fun `should return empty movie details by default`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { movieDetailsResponse.body() } returns null
        every { movieDetailsResponse.isSuccessful } returns true
        every { movieDetailsCall.execute() } returns movieDetailsResponse
        every { service.movieDetails(1) } returns movieDetailsCall

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeEqual Right(MovieDetails.empty)
        verify(exactly = 1) { service.movieDetails(1) }
    }

    @Test
    fun `should get movie details from service`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { movieDetailsResponse.body() } returns
                MovieDetailsEntity(8, "title", String.empty(), String.empty(),
                        String.empty(), String.empty(), 0, String.empty())
        every { movieDetailsResponse.isSuccessful } returns true
        every { movieDetailsCall.execute() } returns movieDetailsResponse
        every { service.movieDetails(1) } returns movieDetailsCall

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeEqual Right(
            MovieDetails(8, "title", String.empty(),
            String.empty(), String.empty(), String.empty(), 0, String.empty())
        )
        verify(exactly = 1) { service.movieDetails(1) }
    }

    @Test
    fun `movie details service should return network failure when no connection`() {
        every { networkHandler.isNetworkAvailable() } returns false

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails.isLeft shouldBeEqual true
        movieDetails.fold({ failure -> failure should beInstanceOf<NetworkConnection>() }, {})
        verify { service wasNot Called }
    }

    @Test
    fun `movie details service should return server error if no successful response`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { movieDetailsResponse.body() } returns null
        every { movieDetailsResponse.isSuccessful } returns false
        every { movieDetailsCall.execute() } returns movieDetailsResponse
        every { service.movieDetails(1) } returns movieDetailsCall

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails should beInstanceOf<Either<Failure, MovieDetails>>()
        movieDetails.isLeft shouldBeEqual true
        movieDetails.fold({ failure -> failure should beInstanceOf<ServerError>() }, {})
    }

    @Test
    fun `movie details request should catch exceptions`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { movieDetailsCall.execute() } returns movieDetailsResponse
        every { service.movieDetails(1) } returns movieDetailsCall

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails.isLeft shouldBeEqual true
        movieDetails.fold({ failure -> failure should beInstanceOf<ServerError>() }, {})
    }
}
