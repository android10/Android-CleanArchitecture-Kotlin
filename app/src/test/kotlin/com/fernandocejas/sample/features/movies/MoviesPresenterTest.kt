package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.UnitTest
import com.nhaarman.mockito_kotlin.anyVararg
import com.nhaarman.mockito_kotlin.argumentCaptor
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

    @Test fun `should dispose view and useCase`() {
        moviesPresenter.destroy()

        verify(getMovies).dispose()
        verify(moviesView).dispose()
    }

    @Test fun `should load movies`() {
        moviesPresenter.loadMovies()

        verify(moviesView).showLoading()
        verify(getMovies).execute(anyVararg(), anyVararg(), anyVararg())
    }

    @Test fun `should render movies`() {
        val onCompleteCaptor = argumentCaptor<(List<Movie>) -> Unit>()

        moviesPresenter.loadMovies()
        verify(getMovies).execute(onCompleteCaptor.capture(), anyVararg(), anyVararg())

        onCompleteCaptor.firstValue.invoke(emptyList())
        verify(moviesView).renderList(emptyList())
        verify(moviesView).hideLoading()
    }
}
