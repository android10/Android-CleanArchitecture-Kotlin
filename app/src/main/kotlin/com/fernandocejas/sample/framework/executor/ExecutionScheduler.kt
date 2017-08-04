package com.fernandocejas.sample.framework.executor

import io.reactivex.Scheduler

interface ExecutionScheduler {
    fun ui(): Scheduler
    fun highPriority(): Scheduler
    fun lowPriority(): Scheduler
}
