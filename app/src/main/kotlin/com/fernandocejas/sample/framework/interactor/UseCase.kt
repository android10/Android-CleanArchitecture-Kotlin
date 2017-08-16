package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T, in Params>(private val scheduler: ExecutionScheduler) {

    private val disposables = CompositeDisposable()

    abstract fun buildObservable(params: Params?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params? = null) {
        disposables.add(buildObservable(params).subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()

    //TODO: check style and reuse code since these methods are similar
    internal fun <T> highPriorityScheduler() = { upstream: Observable<T> -> upstream.subscribeOn(scheduler.highPriority()).observeOn(scheduler.ui()) }
    internal fun <T> lowPriorityScheduler() = { upstream: Observable<T> -> upstream.subscribeOn(scheduler.lowPriority()).observeOn(scheduler.ui()) }
}
