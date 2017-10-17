package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.None
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository,
                    private val scheduler: ExecutionScheduler) : UseCase.RxObservable<List<Movie>, None>() {

    override fun build(params: None?): Observable<List<Movie>> =
            moviesRepository.movies().compose(scheduler.applyHighPriorityScheduler())
}
