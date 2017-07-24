package com.fernandocejas.sample.framework.interactor

import io.reactivex.Observable

abstract class UseCase<T, in P> {

    abstract fun buildObservable(params: P?): Observable<T>

    //TODO: P should be empty rather than @Nullable
    fun execute(observer: UseCaseObserver<T>, params: P?) {
        buildObservable(params).subscribeWith(observer)
    }
}
