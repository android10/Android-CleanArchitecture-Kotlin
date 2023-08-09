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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fernandocejas.sample.core.platform.BaseViewModel
import com.fernandocejas.sample.features.movies.interactor.MovieDetails
import com.fernandocejas.sample.features.movies.interactor.PlayMovie
import com.fernandocejas.sample.features.movies.interactor.GetMovieDetails
import com.fernandocejas.sample.features.movies.interactor.GetMovieDetails.Params

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetails,
    private val playMovie: PlayMovie
) : BaseViewModel() {

    private val _movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()
    val movieDetails: LiveData<MovieDetailsView> = _movieDetails

    fun loadMovieDetails(movieId: Int) =
        getMovieDetails(Params(movieId), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleMovieDetails
            )
        }

    fun playMovie(url: String) = playMovie(PlayMovie.Params(url), viewModelScope)

    private fun handleMovieDetails(movie: MovieDetails) {
        _movieDetails.value = MovieDetailsView(
            movie.id, movie.title, movie.poster,
            movie.summary, movie.cast, movie.director, movie.year, movie.trailer
        )
    }
}
