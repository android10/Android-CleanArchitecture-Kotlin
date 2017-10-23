package com.fernandocejas.sample.features.movies

import android.content.Context
import com.fernandocejas.sample.UnitTest
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MovieDetailsPresenterTest : UnitTest() {

    private val MOVIE_ID = 1
    private val MOVIE_URL = "https://fernandocejas.com"

    private lateinit var movieDetailsPresenter: MovieDetailsPresenter

    @Mock private lateinit var getMovieDetails: GetMovieDetails
    @Mock private lateinit var playMovie: PlayMovie
    @Mock private lateinit var movieDetailsView: MovieDetailsView

    @Before fun setUp() {
        movieDetailsPresenter = MovieDetailsPresenter(getMovieDetails, playMovie)
        movieDetailsPresenter.movieDetailsView = movieDetailsView
    }

    @Test fun `should dispose view and useCases`() {
        movieDetailsPresenter.destroy()

        verify(getMovieDetails).dispose()
        verify(playMovie).dispose()
        verify(movieDetailsView).dispose()
    }

    @Test fun `should load movie details`() {
        movieDetailsPresenter.loadMovieDetails(MOVIE_ID)

        verify(movieDetailsView).showLoading()
        verify(getMovieDetails).execute(anyVararg(), anyVararg(), eq(GetMovieDetails.Params(MOVIE_ID)))
    }

    @Test fun `should render movies`() {
        val onCompleteCaptor = argumentCaptor<(MovieDetails) -> Unit>()

        movieDetailsPresenter.loadMovieDetails(MOVIE_ID)
        verify(getMovieDetails).execute(onCompleteCaptor.capture(), anyVararg(), eq(GetMovieDetails.Params(MOVIE_ID)))

        onCompleteCaptor.firstValue.invoke(MovieDetails.create { })
        verify(movieDetailsView).renderDetails(anyVararg())
        verify(movieDetailsView).hideLoading()
    }

    @Test fun `should play movie`() {
        val context: Context = mock()
        movieDetailsPresenter.playMovie(context, MOVIE_URL)

        verify(playMovie).execute(eq(PlayMovie.Params(context, MOVIE_URL)))
    }
}
