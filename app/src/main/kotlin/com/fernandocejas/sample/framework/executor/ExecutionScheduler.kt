package com.fernandocejas.sample.framework.executor

import io.reactivex.Observable
import io.reactivex.Scheduler

interface ExecutionScheduler {
    fun ui(): Scheduler
    fun highPriority(): Scheduler
    fun lowPriority(): Scheduler
    fun <T> applyHighPriorityScheduler(): (Observable<T>) -> Observable<T>
    fun <T> applyLowPriorityScheduler(): (Observable<T>) -> Observable<T>
}
