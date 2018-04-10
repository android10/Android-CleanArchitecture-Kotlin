package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.features.movies.MoviesRepository.Network
import com.fernandocejas.sample.framework.extension.empty
import com.fernandocejas.sample.framework.functional.Either.Right
import com.fernandocejas.sample.framework.platform.NetworkHandler
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
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
        given { moviesCall.execute() }.willReturn(moviesResponse)
        given { service.movies() }.willReturn(moviesCall)

        val movies = networkRepository.movies()

        movies shouldEqual Right(emptyList<Movie>())
        verify(service).movies()
    }

    @Test fun `should get movie list from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { moviesResponse.body() }.willReturn(listOf(MovieEntity(1, "poster")))
        given { moviesCall.execute() }.willReturn(moviesResponse)
        given { service.movies() }.willReturn(moviesCall)

        val movies = networkRepository.movies()

        movies shouldEqual Right(listOf(Movie(1, "poster")))
        verify(service).movies()
    }

    @Test fun `should return empty details by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieDetailsResponse.body() }.willReturn(null)
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
        given { movieDetailsCall.execute() }.willReturn(movieDetailsResponse)
        given { service.movieDetails(1) }.willReturn(movieDetailsCall)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldEqual Right(MovieDetails(8, "title", String.empty(), String.empty(),
                String.empty(), String.empty(), 0, String.empty()))
        verify(service).movieDetails(1)
    }
}