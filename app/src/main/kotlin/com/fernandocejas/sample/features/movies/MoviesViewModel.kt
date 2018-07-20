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

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.fernandocejas.sample.core.exception.Failure
import com.fernandocejas.sample.core.functional.Either
import com.fernandocejas.sample.core.interactor.UseCase.None
import com.fernandocejas.sample.core.platform.ViewState
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies) : ViewModel() {

    var movies: MutableLiveData<ViewState> = MutableLiveData()

    fun loadMovies() = getMovies(None()) { handleMoviesViewState(it) }

    private fun handleMoviesViewState(either: Either<Failure, List<Movie>>) =
            when (either) {
                is Either.Right -> movies.value = ViewState.Success(either.b.map { MovieView(it.id, it.poster) })
                is Either.Left -> movies.value = ViewState.Error(either.a)
            }
}
