package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.framework.functional.Either.Right
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test fun `loading movies should update live data`() {
        runBlocking {
            val moviesList = listOf(Movie(0, "IronMan"), Movie(1, "Batman"))
            given { moviesRepository.movies() }.willReturn(Right(moviesList))

            moviesViewModel.loadMovies()

            val movies = moviesViewModel.movies.value

            movies!!.size shouldEqualTo 2
            movies[0].id shouldEqualTo 0
            movies[0].poster shouldEqualTo "IronMan"
            movies[1].id shouldEqualTo 1
            movies[1].poster shouldEqualTo "Batman"
        }
    }
}