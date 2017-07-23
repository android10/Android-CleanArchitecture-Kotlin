package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.interactor.UseCaseObserver
import io.reactivex.Observable
import javax.inject.Inject

class GetMovies @Inject constructor() {

    fun execute(observer: UseCaseObserver<List<Movie>>) {
        val movieOne = Movie.create { title = "Iron Man 3" }
        val movieTwo = Movie.create { title = "Superman" }

        val observable: Observable<List<Movie>> = Observable.just(listOf(movieOne, movieTwo))
        observable.subscribeWith(observer)
    }
}
