package com.fernandocejas.sample.framework.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T, in Params> {

    private val disposables = CompositeDisposable()

    abstract fun buildObservable(params: Params?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params? = null) {
        buildObservable(params).subscribeWith(observer)
    }

    fun dispose() = disposables.dispose()
}
