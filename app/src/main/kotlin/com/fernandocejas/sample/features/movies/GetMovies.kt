package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.None
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository,
                    private val scheduler: ExecutionScheduler) : UseCase<List<Movie>, None>() {

    override fun buildObservable(params: None?): Observable<List<Movie>> =
            moviesRepository.movies().compose(scheduler.applyHighPriorityScheduler())
}
