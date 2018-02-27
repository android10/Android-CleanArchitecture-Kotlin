package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.AndroidTest
import com.nhaarman.mockito_kotlin.any
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test

class PlayMovieTest : AndroidTest() {

    private lateinit var playMovie: PlayMovie

    @Before fun setUp() {
        playMovie = PlayMovie(context())
    }

    @Test fun `should play movie trailer`() {
        val params = PlayMovie.Params("https://www.youtube.com/watch?v=fernando")

        playMovie.execute({}, params)

        true shouldBe true //TODO: check shouldNavigateTo
    }
}
