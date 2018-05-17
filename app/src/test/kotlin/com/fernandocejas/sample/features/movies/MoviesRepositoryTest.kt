/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
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
package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.features.movies.MoviesRepository.Network
import com.fernandocejas.sample.core.exception.Failure.NetworkConnection
import com.fernandocejas.sample.core.exception.Failure.ServerError
import com.fernandocejas.sample.core.extension.empty
import com.fernandocejas.sample.core.functional.Either
import com.fernandocejas.sample.core.functional.Either.Left
import com.fernandocejas.sample.core.functional.Either.Right
import com.fernandocejas.sample.core.platform.NetworkHandler
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response

class MoviesRepositoryTest : UnitTest() {

    private lateinit var networkRepository: MoviesRepository.Network

    @Mock private lateinit var networkHandler: NetworkHandler
    @Mock private lateinit var service: MoviesService

    @Mock private lateinit var moviesCall: Call<List<MovieEntity>>
    @Mock private lateinit var moviesResponse: Response<List<MovieEntity>>
    @Mock private lateinit var movieDetailsCall: Call<MovieDetailsEntity>
    @Mock private lateinit var movieDetailsResponse: Response<MovieDetailsEntity>

    @Before fun setUp() {
        networkRepository = Network(networkHandler, service)
    }

    @Test fun `should return empty list by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { moviesResponse.body() }.willReturn(null)
        given { moviesResponse.isSuccessful }.willReturn(true)
        given { moviesCall.execute() }.willReturn(moviesResponse)
        given { service.movies() }.willReturn(moviesCall)

        val movies = networkRepository.movies()

        movies shouldEqual Right(emptyList<Movie>())
        verify(service).movies()
    }

    @Test fun `should get movie list from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { moviesResponse.body() }.willReturn(listOf(MovieEntity(1, "poster")))
        given { moviesResponse.isSuccessful }.willReturn(true)
        given { moviesCall.execute() }.willReturn(moviesResponse)
        given { service.movies() }.willReturn(moviesCall)

        val movies = networkRepository.movies()

        movies shouldEqual Right(listOf(Movie(1, "poster")))
        verify(service).movies()
    }

    @Test fun `movies service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `movies service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `movies service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `movies request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `should return empty movie details by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieDetailsResponse.body() }.willReturn(null)
        given { movieDetailsResponse.isSuccessful }.willReturn(true)
        given { movieDetailsCall.execute() }.willReturn(movieDetailsResponse)
        given { service.movieDetails(1) }.willReturn(movieDetailsCall)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldEqual Right(MovieDetails.empty())
        verify(service).movieDetails(1)
    }

    @Test fun `should get movie details from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieDetailsResponse.body() }.willReturn(
                MovieDetailsEntity(8, "title", String.empty(), String.empty(),
                        String.empty(), String.empty(), 0, String.empty()))
        given { movieDetailsResponse.isSuccessful }.willReturn(true)
        given { movieDetailsCall.execute() }.willReturn(movieDetailsResponse)
        given { service.movieDetails(1) }.willReturn(movieDetailsCall)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldEqual Right(MovieDetails(8, "title", String.empty(), String.empty(),
                String.empty(), String.empty(), 0, String.empty()))
        verify(service).movieDetails(1)
    }

    @Test fun `movie details service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `movie details service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `movie details service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `movie details request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}