package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies @Inject constructor() : UseCase<List<Movie>, Void>() {

    override fun buildObservable(params: Void?): Observable<List<Movie>> {
        val movieOne = Movie.create { title = "Iron Man 3" }
        val movieTwo = Movie.create { title = "Superman" }

        return Observable.just(listOf(movieOne, movieTwo))
    }
}
