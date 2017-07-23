package com.fernandocejas.sample.framework.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    fun scheduler(): Scheduler
}
