package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.framework.interactor.UseCase
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {
    private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Before fun setUp() {
        getMovies = GetMovies(moviesRepository)
    }

    @Test fun shouldGetDataFromRepository() {
        getMovies.buildObservable(UseCase.None())

        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)
    }
}
