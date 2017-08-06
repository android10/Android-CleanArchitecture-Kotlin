package com.fernandocejas.sample.framework.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton class ThreadScheduler
@Inject constructor() : ExecutionScheduler {
    companion object {
        internal const val HIGH_PRIORITY_THREADS = 6
        internal const val LOW_PRIORITY_THREADS = 2

        internal val highPriorityScheduler: Scheduler
        internal val lowPriorityScheduler: Scheduler
        
        init {
            highPriorityScheduler = Schedulers.from(JobExecutor(HIGH_PRIORITY_THREADS, "High-Priority-Pool"))
            lowPriorityScheduler = Schedulers.from(JobExecutor(LOW_PRIORITY_THREADS, "Low-Priority-Pool"))
        }
    }

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun highPriority(): Scheduler = highPriorityScheduler
    override fun lowPriority(): Scheduler = lowPriorityScheduler
}
