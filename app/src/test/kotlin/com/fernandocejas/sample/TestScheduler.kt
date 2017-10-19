package com.fernandocejas.sample

import com.fernandocejas.sample.TestScheduler.Function.*
import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.mock

internal class TestScheduler(private val scheduler: ExecutionScheduler = mock(ExecutionScheduler::class)) : ExecutionScheduler {

    override fun ui(): Scheduler {
        scheduler.ui()
        return Schedulers.trampoline()
    }

    override fun highPriority(): Scheduler {
        scheduler.highPriority()
        return Schedulers.trampoline()
    }

    override fun lowPriority(): Scheduler {
        scheduler.lowPriority()
        return Schedulers.trampoline()
    }

    override fun <T> highPrioritySingle(): (Single<T>) -> Single<T> {
        scheduler.highPrioritySingle<T>()
        return { upstream: Single<T> -> upstream.subscribeOn(highPriority()).observeOn(ui()) }
    }

    override fun <T> lowPrioritySingle(): (Single<T>) -> Single<T> {
        scheduler.lowPrioritySingle<T>()
        return { upstream: Single<T> -> upstream.subscribeOn(lowPriority()).observeOn(ui()) }
    }

    override fun <T> highPriorityObservable(): (Observable<T>) -> Observable<T> {
        scheduler.highPriorityObservable<T>()
        return { upstream: Observable<T> -> upstream.subscribeOn(highPriority()).observeOn(ui()) }
    }

    override fun <T> lowPriorityObservable(): (Observable<T>) -> Observable<T> {
        scheduler.lowPriorityObservable<T>()
        return { upstream: Observable<T> -> upstream.subscribeOn(lowPriority()).observeOn(ui()) }
    }

    override fun <T> highPriorityFlowable(): (Flowable<T>) -> Flowable<T> {
        scheduler.highPriorityFlowable<T>()
        return { upstream: Flowable<T> -> upstream.subscribeOn(highPriority()).observeOn(ui()) }
    }

    override fun <T> lowPriorityFlowable(): (Flowable<T>) -> Flowable<T> {
        scheduler.lowPriorityFlowable<T>()
        return { upstream: Flowable<T> -> upstream.subscribeOn(lowPriority()).observeOn(ui()) }
    }

    override fun highPriorityCompletable(): (Completable) -> Completable {
        scheduler.highPriorityCompletable()
        return { upstream: Completable -> upstream.subscribeOn(highPriority()).observeOn(ui()) }
    }

    override fun lowPriorityCompletable(): (Completable) -> Completable {
        scheduler.lowPriorityCompletable()
        return { upstream: Completable -> upstream.subscribeOn(lowPriority()).observeOn(ui()) }
    }

    internal infix fun verify(function: Function) {
        when (function) {
            is ui -> verify(scheduler).ui()
            is highPriority -> verify(scheduler).highPriority()
            is lowPriority -> verify(scheduler).lowPriority()

            is highPrioritySingle -> verify(scheduler).highPrioritySingle<Any>()
            is lowPrioritySingle -> verify(scheduler).lowPrioritySingle<Any>()

            is highPriorityObservable -> verify(scheduler).highPriorityObservable<Any>()
            is lowPriorityObservable -> verify(scheduler).lowPriorityObservable<Any>()

            is highPriorityFlowable -> verify(scheduler).highPriorityFlowable<Any>()
            is lowPriorityFlowable -> verify(scheduler).lowPriorityFlowable<Any>()

            is highPriorityCompletable -> verify(scheduler).highPriorityCompletable()
            is lowPriorityCompletable -> verify(scheduler).lowPriorityCompletable()
        }
    }

    internal sealed class Function {
        object ui : Function()
        object highPriority : Function()
        object lowPriority : Function()

        object highPrioritySingle : Function()
        object lowPrioritySingle : Function()

        object highPriorityObservable : Function()
        object lowPriorityObservable : Function()

        object highPriorityFlowable : Function()
        object lowPriorityFlowable : Function()

        object highPriorityCompletable : Function()
        object lowPriorityCompletable : Function()
    }
}
