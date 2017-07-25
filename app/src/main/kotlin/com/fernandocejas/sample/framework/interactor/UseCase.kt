package com.fernandocejas.sample.framework.interactor

import io.reactivex.Observable

abstract class UseCase<T, in Params> {

    abstract fun buildObservable(params: Params): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params = null!!) {
        buildObservable(params).subscribeWith(observer)
    }
}
