/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
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
package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.framework.functional.Either.Right
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var getMovies: GetMovies

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test fun `loading movies should update live data`() {
        val moviesList = listOf(Movie(0, "IronMan"), Movie(1, "Batman"))
        given { moviesRepository.movies() }.willReturn(Right(moviesList))

        runBlocking { moviesViewModel.loadMovies() }

        val movies = moviesViewModel.movies.value

        movies!!.size shouldEqualTo 2
        movies[0].id shouldEqualTo 0
        movies[0].poster shouldEqualTo "IronMan"
        movies[1].id shouldEqualTo 1
        movies[1].poster shouldEqualTo "Batman"
    }
}