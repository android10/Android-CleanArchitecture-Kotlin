package com.fernandocejas.sample.framework.interactor

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T, in Params>(internal val scheduler: ExecutionScheduler) {

    private val disposables = CompositeDisposable()

    abstract fun buildObservable(params: Params?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params? = null) {
        disposables.add(buildObservable(params).subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()

    fun <T> highPriorityScheduling(): ObservableTransformer<T, T> {
         return ObservableTransformer { observable ->
             with(observable) {
                subscribeOn(scheduler.highPriority())
                observeOn(scheduler.ui())
             }
         }
    }

    private fun <T> lowPriorityScheduling(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            with(observable) {
                subscribeOn(scheduler.lowPriority())
                observeOn(scheduler.ui())
            }
        }
    }


}
