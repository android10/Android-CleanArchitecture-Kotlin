package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.None
import io.reactivex.Single
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository,
                    private val scheduler: ExecutionScheduler) : UseCase.RxSingle<List<Movie>, None>() {

    override fun build(params: None?): Single<List<Movie>> =
            moviesRepository.movies().compose(scheduler.highPrioritySingle())
}
