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
import com.fernandocejas.sample.features.movies.interactor.GetMovies
import com.fernandocejas.sample.features.movies.interactor.Movie
import com.fernandocejas.sample.features.movies.ui.MoviesViewModel
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: MoviesViewModel

    @MockK
    private lateinit var getMovies: GetMovies

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test
    fun `loading movies should update live data`() {
        val moviesList = listOf(Movie(0, "IronMan"), Movie(1, "Batman"))
        coEvery { getMovies.run(any()) } returns Right(moviesList)

        moviesViewModel.movies.observeForever {
            it!!.size shouldBeEqual 2
            it[0].id shouldBeEqual 0
            it[0].poster shouldBeEqual "IronMan"
            it[1].id shouldBeEqual 1
            it[1].poster shouldBeEqual "Batman"
        }

        runBlocking { moviesViewModel.loadMovies() }
    }
}
