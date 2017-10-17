package com.fernandocejas.sample.framework.interactor

import io.reactivex.observers.DisposableSingleObserver

open class UseCaseSingleObserver<T> : DisposableSingleObserver<T>() {
    override fun onSuccess(t: T) {
        //no op by default
    }

    override fun onError(e: Throwable) {
        //TODO: default error handling
    }
}
