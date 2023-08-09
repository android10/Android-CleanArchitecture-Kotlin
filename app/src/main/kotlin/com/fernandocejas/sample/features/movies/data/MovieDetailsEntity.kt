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
package com.fernandocejas.sample.features.movies.data

import com.fernandocejas.sample.core.extension.empty
import com.fernandocejas.sample.features.movies.interactor.MovieDetails

data class MovieDetailsEntity(
    private val id: Int,
    private val title: String,
    private val poster: String,
    private val summary: String,
    private val cast: String,
    private val director: String,
    private val year: Int,
    private val trailer: String
) {

    companion object {
        val empty = MovieDetailsEntity(
            0, String.empty(), String.empty(), String.empty(),
            String.empty(), String.empty(), 0, String.empty()
        )
    }

    fun toMovieDetails() = MovieDetails(id, title, poster, summary, cast, director, year, trailer)
}
