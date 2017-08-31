package com.fernandocejas.sample.framework.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T, in P> where P : Any {

    internal val disposables = CompositeDisposable()

    abstract fun buildObservable(params: P?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: P? = null) {
        disposables
                .add(buildObservable(params)
                .subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()

    class None
}
