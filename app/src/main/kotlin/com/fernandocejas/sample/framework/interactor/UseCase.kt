package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T, in Params>(private val scheduler: ExecutionScheduler) {

    private val disposables = CompositeDisposable()

    abstract fun buildObservable(params: Params?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params? = null) {
        val observable = buildObservable(params)
        with(observable) {
            subscribeOn(scheduler.highPriority())
            observeOn(scheduler.ui())
            disposables.add(subscribeWith(observer))
        }
    }

    fun dispose() = disposables.dispose()
}
