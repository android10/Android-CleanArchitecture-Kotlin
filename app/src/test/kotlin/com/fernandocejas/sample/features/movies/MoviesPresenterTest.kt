package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.features.movies.MoviesPresenter.MoviesObserver
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesPresenterTest : UnitTest() {

    private lateinit var moviesPresenter: MoviesPresenter

    @Mock private lateinit var getMovies: GetMovies
    @Mock private lateinit var moviesView: MoviesView

    @Before fun setUp() {
        moviesPresenter = MoviesPresenter(getMovies)
        moviesPresenter.moviesView = moviesView
    }

    @Test fun shouldDisposeViewAndUseCase() {
        moviesPresenter.destroy()

        verify(getMovies).dispose()
        verify(moviesView).dispose()
    }

    @Test fun shouldLoadMovies() {
        moviesPresenter.loadMovies()

        verify(moviesView).showLoading()
        verify(getMovies).execute(eq(any()))
    }

    @Test fun shouldRenderMovies() {
        val moviesObserverCaptor = argumentCaptor<MoviesObserver>()

        moviesPresenter.loadMovies()

        verify(getMovies).execute(eq(moviesObserverCaptor.capture()))

        val moviesObserver = moviesObserverCaptor.firstValue

        moviesObserver.onNext(emptyList())
        moviesObserver.onComplete()

        verify(moviesView).renderList(emptyList())
        verify(moviesView).hideLoading()
    }
}
