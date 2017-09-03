package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.features.movies.GetMovieDetails.Params
import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.interactor.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieDetails
@Inject constructor(private val moviesRepository: MoviesRepository,
                    private val scheduler: ExecutionScheduler) : UseCase<MovieDetails, Params>() {

    override fun buildObservable(params: Params?): Observable<MovieDetails> =
        moviesRepository.movieDetails(params!!.id).compose(scheduler.applyHighPriorityScheduler())

    class Params(val id: Int)
}
