package com.fernandocejas.sample.framework.interactor

import android.util.Log
import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, in Params>(private val scheduler: ExecutionScheduler) {

    private val disposables = CompositeDisposable()

    abstract fun buildObservable(params: Params?): Observable<T>

    fun execute(observer: UseCaseObserver<T>, params: Params? = null) {
        val observable = buildObservable(params)

        observable.subscribeOn(Schedulers.newThread())
        observable.observeOn(AndroidSchedulers.mainThread())
        //-------
        observable.doOnNext { Log.d("FernandoAndroid", "Fer -----> " + it.toString()) }
        observable.subscribe { Log.d("FernandoAndroid", "Fer -----> " + it.toString()) }
        //-------
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()
}
