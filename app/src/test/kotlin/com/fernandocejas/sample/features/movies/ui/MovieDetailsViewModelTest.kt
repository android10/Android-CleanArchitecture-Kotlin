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
package com.fernandocejas.sample.features.movies.ui

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.core.functional.Either.Right
import com.fernandocejas.sample.features.movies.interactor.GetMovieDetails
import com.fernandocejas.sample.features.movies.interactor.MovieDetails
import com.fernandocejas.sample.features.movies.interactor.PlayMovie
import com.fernandocejas.sample.features.movies.ui.MovieDetailsViewModel
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieDetailsViewModelTest : AndroidTest() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @MockK
    private lateinit var getMovieDetails: GetMovieDetails

    @MockK
    private lateinit var playMovie: PlayMovie

    @Before
    fun setUp() {
        movieDetailsViewModel = MovieDetailsViewModel(getMovieDetails, playMovie)
    }

    @Test
    fun `loading movie details should update live data`() {
        val movieDetails = MovieDetails(0, "IronMan", "poster", "summary",
                "cast", "director", 2018, "trailer")
        coEvery { getMovieDetails.run(any()) } returns Right(movieDetails)

        movieDetailsViewModel.movieDetails.observeForever {
            with(it!!) {
                id shouldBeEqual 0
                title shouldBeEqual "IronMan"
                poster shouldBeEqual "poster"
                summary shouldBeEqual "summary"
                cast shouldBeEqual "cast"
                director shouldBeEqual "director"
                year shouldBeEqual 2018
                trailer shouldBeEqual "trailer"
            }
        }

        runBlocking { movieDetailsViewModel.loadMovieDetails(0) }
    }
}
