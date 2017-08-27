package com.fernandocejas.sample.framework.interactor

import io.reactivex.observers.DisposableObserver

open class UsecaseDisposableObserver<T>(
        private val onUseCaseComplete: () -> Unit = { },
        private val onUseCaseNext: (T) -> Unit = { },
        private val onUseCaseError: (Throwable) -> Unit = { })
    : DisposableObserver<T>() {

    override fun onComplete() = onUseCaseComplete()
    override fun onNext(response: T) = onUseCaseNext(response)
    override fun onError(e: Throwable) = onUseCaseError(e)
}
