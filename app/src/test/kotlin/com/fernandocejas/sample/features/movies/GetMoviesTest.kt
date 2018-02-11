package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.TestScheduler
import com.fernandocejas.sample.TestScheduler.Function.highPrioritySingle
import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.framework.interactor.UseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {

    private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    private val testScheduler = TestScheduler()

    @Before fun setUp() {
        getMovies = GetMovies(moviesRepository, testScheduler)
        given { moviesRepository.movies() }.willReturn(Single.just(listOf(Movie.empty())))
    }

    @Test fun `should get data from repository`() {
        getMovies.build(UseCase.None())

        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)

        testScheduler verify highPrioritySingle
    }
}
