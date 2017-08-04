package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCase
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, Void>() {

    override fun buildObservable(params: Void?) = moviesRepository.movies()
}
