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

import com.fernandocejas.sample.core.network.ApiResponse
import com.fernandocejas.sample.core.network.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class MoviesService(
    private val httpClient: HttpClient,
) {

    suspend fun movies(): ApiResponse<List<MovieEntity>, Unit> =
        httpClient.safeRequest<List<MovieEntity>, Unit> {
            url(BASE_URL + MOVIES)
            contentType(ContentType.Text.Plain)
        }

    suspend fun movieDetails(movieId: Int): ApiResponse<MovieDetailsEntity, Unit> =
        httpClient.safeRequest<MovieDetailsEntity, Unit> {
            url(BASE_URL + "movie_0${movieId}.json")
            contentType(ContentType.Text.Plain)
        }

    companion object {
        private const val MOVIES = "movies.json"
        private const val BASE_URL =
            "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/"
    }
}
