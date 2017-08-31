package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.TestScheduler
import com.fernandocejas.sample.TestScheduler.Function.applyHighPriorityScheduler
import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.framework.extension.empty
import com.fernandocejas.sample.framework.interactor.UseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {
    private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    private val testScheduler = TestScheduler()

    @Before fun setUp() {
        getMovies = GetMovies(moviesRepository, testScheduler)
        given { moviesRepository.movies() } .willReturn(Observable.just(createMovieList()))
    }

    @Test fun shouldGetDataFromRepository() {
        getMovies.buildObservable(UseCase.None())

        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)

        testScheduler called applyHighPriorityScheduler
    }

    private fun createMovieList() = listOf(Movie.create { String.empty() })
}
