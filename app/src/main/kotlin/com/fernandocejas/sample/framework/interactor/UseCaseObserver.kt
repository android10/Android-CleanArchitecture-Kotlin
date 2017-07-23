package com.fernandocejas.sample.framework.interactor

import io.reactivex.observers.DisposableObserver

open class UseCaseObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {
        //no op by default
    }

    override fun onNext(value: T) {
        //no op by default
    }

    override fun onError(e: Throwable?) {
        //TODO: default error handling
    }
}
