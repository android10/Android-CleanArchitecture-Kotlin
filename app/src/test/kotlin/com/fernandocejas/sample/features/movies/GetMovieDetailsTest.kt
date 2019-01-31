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

import com.fernandocejas.sample.UnitTest
import com.fernandocejas.sample.core.functional.Either.Right
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMovieDetailsTest : UnitTest() {

    private val MOVIE_ID = 1

    private lateinit var getMovieDetails: GetMovieDetails

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Mock private lateinit var scope: CoroutineScope

    @Mock private lateinit var dispatcher: CoroutineDispatcher

    @Before fun setUp() {
        getMovieDetails = GetMovieDetails(moviesRepository, scope, dispatcher)
        given { moviesRepository.movieDetails(MOVIE_ID) }.willReturn(Right(MovieDetails.empty))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getMovieDetails.run(GetMovieDetails.Params(MOVIE_ID)) }

        verify(moviesRepository).movieDetails(MOVIE_ID)
        verifyNoMoreInteractions(moviesRepository)
    }
}
