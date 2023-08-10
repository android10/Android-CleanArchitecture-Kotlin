/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.movies.interactor

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.core.navigation.Navigator
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PlayMovieTest : AndroidTest() {

    private lateinit var playMovie: PlayMovie

    private val context = context()

    @MockK
    private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        playMovie = PlayMovie(context, navigator)
    }

    @Test
    fun `should play movie trailer`() {
        val params = PlayMovie.Params(VIDEO_URL)

        runBlocking { playMovie.run(params) }

        verify(exactly = 1) { navigator.openVideo(context, VIDEO_URL) }
    }

    companion object {
        private const val VIDEO_URL = "https://www.youtube.com/watch?v=fernando"
    }
}
