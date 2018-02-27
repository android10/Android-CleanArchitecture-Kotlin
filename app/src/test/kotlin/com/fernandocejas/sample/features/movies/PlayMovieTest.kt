package com.fernandocejas.sample.features.movies

import android.content.Context
import com.fernandocejas.sample.AndroidTest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PlayMovieTest : AndroidTest() {

    private lateinit var playMovie: PlayMovie

    @Mock private lateinit var context: Context

    @Before fun setUp() {
        playMovie = PlayMovie(context)
    }

    @Test fun `should play movie trailer`() {
        val params = PlayMovie.Params("https://www.youtube.com/watch?v=fernando")

        playMovie.execute({}, params)

        verify(context).startActivity(any())
    }
}
