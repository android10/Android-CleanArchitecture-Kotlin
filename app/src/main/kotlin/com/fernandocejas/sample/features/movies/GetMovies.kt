package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.EmptyParams
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository, scheduler: ExecutionScheduler) : UseCase<List<Movie>, EmptyParams>(scheduler) {
    override fun buildObservable(params: EmptyParams?): Observable<List<Movie>> {
        return moviesRepository.movies().compose(highPriorityScheduler())
    }
}
