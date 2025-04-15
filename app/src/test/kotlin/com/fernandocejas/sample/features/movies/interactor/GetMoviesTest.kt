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

import com.fernandocejas.sample.core.functional.toRight
import com.fernandocejas.sample.core.interactor.UseCase
import com.fernandocejas.sample.features.movies.data.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetMoviesTest {

    private val moviesRepository: MoviesRepository = mockk()
    private val getMovies = GetMovies(moviesRepository)

    @Test
    fun `should get data from repository`() = runTest {
        coEvery { moviesRepository.movies() } returns listOf(Movie.empty).toRight()

        getMovies.run(UseCase.None())

        coVerify(exactly = 1) { moviesRepository.movies() }
    }
}
