package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class UseCase<T, in Params> {

    private val disposables = CompositeDisposable()

    @Inject internal lateinit var scheduler: ExecutionScheduler

    abstract fun buildObservable(params: Params?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params? = null) {
        disposables.add(buildObservable(params).subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()

    internal fun <T> highPriorityScheduler() = scheduler.applyHighPriorityScheduler<T>()
    internal fun <T> lowPriorityScheduler() = scheduler.applyLowPriorityScheduler<T>()

    class EmptyParams
}
