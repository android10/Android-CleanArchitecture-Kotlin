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

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.core.functional.Either.Right
import com.fernandocejas.sample.core.interactor.UseCase
import com.fernandocejas.sample.features.movies.data.MoviesRepository
import com.fernandocejas.sample.features.movies.interactor.GetMovies
import com.fernandocejas.sample.features.movies.interactor.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesTest : UnitTest() {

    private lateinit var getMovies: GetMovies

    private val moviesRepository: MoviesRepository = mockk()

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
        every { moviesRepository.movies() } returns Right(listOf(Movie.empty))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovies.run(UseCase.None()) }

        verify(exactly = 1) { moviesRepository.movies() }
    }
}
