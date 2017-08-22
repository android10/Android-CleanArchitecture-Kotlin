package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCase
import com.fernandocejas.sample.framework.interactor.UseCase.None
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, None>() {
    override fun buildObservable(params: None?): Observable<List<Movie>> {
        return moviesRepository.movies().compose(highPriorityScheduler())
    }
}
