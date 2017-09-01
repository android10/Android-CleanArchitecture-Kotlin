package com.fernandocejas.sample

import com.fernandocejas.sample.TestScheduler.Function.*
import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.Scheduler
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

    override fun <T> applyHighPriorityScheduler(): (Observable<T>) -> Observable<T> {
        scheduler.applyHighPriorityScheduler<T>()
        return applyTestSchedulers()
    }

    override fun <T> applyLowPriorityScheduler(): (Observable<T>) -> Observable<T> {
        scheduler.applyLowPriorityScheduler<T>()
        return applyTestSchedulers()
    }

    private fun <T> applyTestSchedulers() = { upstream: Observable<T> ->
        upstream
                .subscribeOn(lowPriority())
                .observeOn(ui())
    }

    internal infix fun verify(function: Function) {
        when (function) {
            is ui -> verify(scheduler).ui()
            is highPriority -> verify(scheduler).highPriority()
            is lowPriority -> verify(scheduler).lowPriority()
            is applyHighPriorityScheduler -> verify(scheduler).applyHighPriorityScheduler<Any>()
            is applyLowPriorityScheduler -> verify(scheduler).applyLowPriorityScheduler<Any>()
        }
    }

    internal sealed class Function {
        object ui : Function()
        object highPriority : Function()
        object lowPriority : Function()
        object applyHighPriorityScheduler : Function()
        object applyLowPriorityScheduler : Function()
    }
}
