package com.fernandocejas.sample.framework.interactor

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

abstract class UseCaseCompletable<in P> where P : Any {

    internal val disposables = CompositeDisposable()

    abstract fun buildCompletable(params: P?): Completable

    fun execute(params: P? = null) {
        disposables.add(buildCompletable(params).subscribe())
    }

    fun dispose() = disposables.dispose()

    class None
}
