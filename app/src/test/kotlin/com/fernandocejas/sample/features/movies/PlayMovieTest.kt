package com.fernandocejas.sample.features.movies

import android.content.Context
import com.fernandocejas.sample.AndroidTest
import com.nhaarman.mockito_kotlin.anyVararg
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class PlayMovieTest : AndroidTest() {

    private lateinit var playMovie: PlayMovie

    @Before fun setUp() {
        playMovie = PlayMovie()
    }

    @Test fun `should play movie trailer`() {
        val context: Context = mock()
        val params = PlayMovie.Params(context, "https://www.youtube.com/watch?v=fernando")

        playMovie.execute(params)

        verify(context).startActivity(anyVararg())
    }
}
