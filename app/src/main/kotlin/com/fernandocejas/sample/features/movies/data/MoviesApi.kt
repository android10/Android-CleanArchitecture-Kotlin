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

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIES = "movies.json"
        private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET(MOVIES)
    fun movies(): Call<List<MovieEntity>>

    @GET(MOVIE_DETAILS)
    fun movieDetails(@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailsEntity>
}
