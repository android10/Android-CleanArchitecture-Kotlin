package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.framework.functional.Either.Right
import com.fernandocejas.sample.navigation.Navigator
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MovieDetailsViewModelTest : AndroidTest() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var getMovieDetails: GetMovieDetails
    private lateinit var playMovie: PlayMovie

    @Mock private lateinit var moviesRepository: MoviesRepository
    @Mock private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        playMovie = PlayMovie(context(), navigator)
        getMovieDetails = GetMovieDetails(moviesRepository)
        movieDetailsViewModel = MovieDetailsViewModel(getMovieDetails, playMovie)
    }

    @Test
    fun `loading movie details should update live data`() {
        runBlocking {
            val movieDetails = MovieDetails(0, "IronMan", "poster", "summary",
                    "cast", "director", 2018, "trailer")
            given { moviesRepository.movieDetails(0) }.willReturn(Right(movieDetails))

            movieDetailsViewModel.loadMovieDetails(0)

            val movie = movieDetailsViewModel.movieDetails.value

            with(movie!!) {
                id shouldEqualTo 0
                title shouldEqualTo "IronMan"
                poster shouldEqualTo "poster"
                summary shouldEqualTo "summary"
                cast shouldEqualTo "cast"
                director shouldEqualTo "director"
                year shouldEqualTo 2018
                trailer shouldEqualTo "trailer"
            }
        }
    }
}