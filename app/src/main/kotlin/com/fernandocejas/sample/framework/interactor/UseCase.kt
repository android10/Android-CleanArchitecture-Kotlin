package com.fernandocejas.sample.framework.interactor

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<out Type, in Params> {

    protected val disposables = CompositeDisposable()

    fun dispose() = disposables.dispose()

    abstract fun build(params: Params?): Type

    abstract class RxSingle<T, in P> : UseCase<Single<T>, P>() {
        fun execute(observer: UseCaseSingleObserver<T>, params: P? = null) =
                disposables.add(build(params).subscribeWith(observer))

        fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) =
            disposables.add(build(params).subscribe(onSuccess, onError))
    }

    abstract class RxObservable<T, in P> : UseCase<Observable<T>, P>() {
        fun execute(observer: UseCaseObserver<T>, params: P? = null) =
            disposables.add(build(params).subscribeWith(observer))

        fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) {
            disposables.add(build(params).subscribe(onNext, onError))
        }
    }

    abstract class RxCompletable<in P> : UseCase<Completable, P>() {
        fun execute(params: P? = null) = execute({}, params)

        fun execute(onComplete: () -> Unit = {}, params: P? = null) =
                disposables.add(build(params).subscribe(onComplete))
    }

    class None
}
