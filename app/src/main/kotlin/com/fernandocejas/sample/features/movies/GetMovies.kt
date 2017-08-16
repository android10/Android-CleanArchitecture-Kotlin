package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.EmptyParams
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, EmptyParams>() {
    override fun buildObservable(params: EmptyParams?): Observable<List<Movie>> {
        return moviesRepository.movies().compose(highPriorityScheduler())
    }
}
